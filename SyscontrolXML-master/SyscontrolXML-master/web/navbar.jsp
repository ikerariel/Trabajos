<%-- 
    Document   : navbar
    Created on : 22-jun-2019, 11:33:39
    Author     : !mX - Made on Earth by Humans
--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <button class="btn btn-primary" id="menu-toggle">Menu</button>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">

            <li class="nav-item">

                <span >Bienvenido, ${sessionScope.sessionON}.</span>  
                <input class="form-control" id="vCodIDuser" style="display: none"value="${sessionScope.sessionCod}" type="text">
                <input class="form-control" id="vUsername" style="display: none"value="${sessionScope.sessionON}" type="text">
            </li>
        </ul>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item">

                <a class="nav-link" href="out.jsp">Salir</a>
            </li>
        </ul>
    </div>
</nav>