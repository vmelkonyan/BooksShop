<#import "parts/common.ftlh" as c>
<#import "parts/login_p.ftlh" as l>
<#include "parts/security.ftlh">
<#import "parts/page.ftlh" as p>
<@c.page>
    <div>
        <#if isAdmin>
            <p>
                <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button"
                   aria-expanded="false"
                   aria-controls="collapseExample">
                    Add new book
                </a>
            </p>
        </#if>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                <form method="post" class="form-row">
                    <div style="display: contents">
                        <input type="text" class="form-control col-sm-3" id="bookName" name="bookName"
                               placeholder="Add book name" required>

                        <input type="text" class="form-control col-sm-3 ml-3" id="authorsName" name="authorsName"
                               placeholder="Add author name" required>

                        <input type="number" min="0" class="form-control col-sm-3 ml-3" id="availableBook"
                               name="availableBook"
                               placeholder="Add book count" required>

                        <input type="number" class="form-control col-sm-3 mt-3" id="pageCount" name="pageCount"
                               placeholder="Set pageCount" required>
                        <input id="datepicker" width="276" name="publishedDate"/>
                    </div>
                    <div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary ml-3 mt-3">Add Book</button>
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
                <th scope="col">Name</th>
                <th scope="col">Published Date</th>
                <th scope="col">Available Book</th>
                <th scope="col">Page Count</th>
            </tr>
            </thead>
            <tbody>
            <#list page.getContent() as book>
                <tr>
                    <td>${book.bookName}</td>
                    <td>${book.publishedDate}</td>
                    <td>${book.availableBook}</td>
                    <td>${book.pageCount}</td>
                </tr>
            <#else >
                No Books
            </#list>
            </tbody>
        </table>
    </div>
    <@p.pager url page />
</@c.page>