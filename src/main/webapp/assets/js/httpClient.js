window.requests = 0;

const httpClient = {
    async get(path, params = {}) {
        return await this.process(axios.get(path, { params }));
    },
    async delete(path) {
        return await this.process(axios.delete(path));
    },
    async post(path, params = {}) {
        return await this.process(axios.post(path, params));
    },
    async process(promise) {
        $("#loader").show();
        window.requests++;
        return await promise.then((res) => {
            window.requests--;
            if (res.status != 200) {
                showAlert({
                    type: "warning",
                    title: "",
                    message: res.data.message
                });
                $("#loader").hide();
                return { status: false, ...res.data };
            }
            if (res.status == 200 && res.data.message) {
                showAlert({
                    type: "success",
                    title: "",
                    message: res.data.message
                });
            }
            if (window.requests == 0) $("#loader").hide();
            return { status: true, ...res.data };
        }).catch((error) => {
            $("#loader").hide();
            if (error.response) {
                const response = error.response.data;
                showAlert({
                    type: "danger",
                    title: "",
                    message: "Error interno de servidor"
                });
                return { status: false, ...error.response.data }
                // error interno del servidor
            } else {
                // si entra a qui es por que la sesion con cas finalizo y hay que relogear
             }
            return { status: false }
        });
    }
}


function showAlert(op, exit = false, time = 5 * 1000) {
    let alert = $('#my-alert');
    if (op.div) {
        alert = $(op.div);
    }
    alert.removeAttr("class");
    alert.html(`
        <div class="alert alert-${op.type} mt-5" role="alert">
            <i class="fas ${op.icon ? op.icon : ''}"></i>
            <button type="button" class="close" data-dismiss="alert" aria-label="Cerrar">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${op.title}</strong> ${op.message}
        </div>
    `);
    alert.show();
    if (exit) setTimeout(() => alert.hide(), time)
    $("html, body").animate({ scrollTop: "0" }, 500);
}
 
function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function validarLetras(e) {
    var expresion = /[a-z A-Z\b ñ Ñ á é í ó ú Á É Í Ó Ú]/;
    return expresion.test(String.fromCharCode(e.which));
}


function validarNumeros(e) {
    var expresion = /[0-9\b]/;
    return expresion.test(String.fromCharCode(e.which));
}

function maxLengthCheck(object) {
    if (object.value.length > object.maxLength)
        object.value = object.value.slice(0, object.maxLength)
}

$.fn.focusWithoutScrolling = function () {
    this.focus();
    $([document.documentElement, document.body]).animate({
        scrollTop: this.offset().top - 150
    }, 500);
    return this;
};