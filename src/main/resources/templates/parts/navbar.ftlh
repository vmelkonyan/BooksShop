<#import "login_p.ftlh" as l>
<#include "security.ftlh">
<nav class=" navbar-expand-lg  navbar navbar-dark bg-primary">
    <a class="navbar-brand" href="#">Book Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <#if !isActive>
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </#if>
            <#if isActive>
                <li class="nav-item active">
                    <a class="nav-link" href="/book">Books</a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="/author">Authors</a>
                </li>
            </#if>
        </ul>
        <div>
            <@l.logout />
        </div>
        <div class="navbar-text ml-3"> <span style="color: white; "> ${name}</div>
    </div>
</nav>