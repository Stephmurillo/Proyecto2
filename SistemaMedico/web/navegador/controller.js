
var urlFront = "http://localhost:8080/SistemaMedico/";
var urlBack = "http://localhost:8080/SistemaMedicoBackend/";
function loadedNav() {
    let nav = document.getElementById("sideBar");
    nav.innerHTML = ` <div><img src=https://drive.google.com/uc?export=view&id=1aWmbSZADIAOqZZ-TZ6IxTcCO72rDiUn1 /></div>
                    <ul id="options">   
                        <li id="agenda"><i class="far fa-file-word"></i><label>AGENDA</label></li>
                        <li id="historial"><i class="fas fa-cog"></i><label>HISTORIAL</label></li>
                        <li id="mantenimiento"><i class="fas fa-user"></i><label>MANTENIMIENTO PACIENTES</label></li>
                        <li id="logout"><i class="fas fa-sign-out-alt"></i><label>SALIR</label></li>
                    </ul> <span class="cross-icon"><i class="fas fa-times"></i></span>`;

    document.getElementById("agenda").addEventListener("click", e => {
        document.location = urlFront + "perfil/agenda.html";
    });
    document.getElementById("historial").addEventListener("click", e => {
        document.location = urlFront + "historial/historialc.html";
    });
    document.getElementById("mantenimiento").addEventListener("click", e => {
        document.location = urlFront + "mantenimiento/mantenimientoc.html";
    });
    document.getElementById("logout").addEventListener("click", logout);
    $("#mobile").click(function () {
        $('.sideBar').addClass("showMenu");
        $('.sideBar').removeClass("widthChange");
        $('.backdrop').addClass('showBackdrop');
    });
    $(".cross-icon").click(function () {
        $('.sideBar').removeClass("showMenu");
        $('.backdrop').removeClass('showBackdrop');
    });
    $(".backdrop").click(function () {
        $('.sideBar').removeClass("showMenu");
        $('.backdrop').removeClass('showBackdrop');
    });
    $("#desktop").click(function () {
        $('li label').toggleClass("hideMenuList");
        $('.sideBar').toggleClass("widthChange");
    });
}

function logout() {
    let request = new Request(urlBack + 'api/login', {method: 'DELETE', headers: {}});
    (async () => {
        const response = await fetch(request);
        if (!response.ok) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Algo salio mal'
            });
        } else {
            sessionStorage.removeItem('user');
            document.location = urlFront + "index.html";
        }
    })();
}

document.addEventListener("DOMContentLoaded", loadedNav);

