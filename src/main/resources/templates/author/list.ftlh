<#import "../parts/common.ftlh" as c>
<#import "../parts/login_p.ftlh" as l>
<#include "../parts/security.ftlh">
<#import "../parts/page.ftlh" as p>
<@c.page>
    <div>
        <#if isAdmin>
            <p>
                <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
                   aria-expanded="false"
                   aria-controls="collapseExample">
                    Add new Author
                </a>
            </p>
        </#if>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                <form method="post" class="form-row" action="/author">
                    <div style="display: contents">
                        <input type="text" class="form-control col-sm-3" id="firstName" name="firstName"
                               placeholder="Add book name" required>

                        <input type="text" class="form-control col-sm-3 ml-3" id="lastName" name="lastName"
                               placeholder="Add author name" required>
                    </div>
                    <div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary ml-3">Add Author</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <@p.pager url page />
    <div class="list-group">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Written count</th>

            </tr>
            </thead>
            <tbody>
            <#list page.getContent() as author>
                <tr>
                    <td>${author.firstName}</td>
                    <td>${author.lastName}</td>
                    <td>${author.bookWrittenCount}</td>
                </tr>
            <#else >
                No Authors
            </#list>
            </tbody>
        </table>
    </div>
    <@p.pager url page />
</@c.page>