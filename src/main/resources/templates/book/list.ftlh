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
                    Add new book
                </a>
            </p>
        </#if>
        <div class="collapse" id="collapseExample">
            <div class="card card-body">
                <form method="post" class="form-row" action="/book">
                    <div style="display: contents" class="row">
                        <input type="text" class="form-control col-sm-3" id="bookName" name="bookName"
                               placeholder="Add book name" required>

                        <input type="number" min="0" class="form-control col-sm-3 ml-3" id="availableBook"
                               name="availableBook"
                               placeholder="Add book count" required>

                        <input type="number" class="form-control col-sm-3 ml-3" id="pageCount" name="pageCount"
                               placeholder="Set pageCount" required>

                        <input type="text" id="datepicker" class="form-control col-sm-3 mt-3" name="publishedDate"
                               required/>

                        <select class="mdb-select md-form form-control col-sm-3 ml-3 mt-3" multiple name="authorsIds"
                                required>

                            <#list authors as author>
                                <option value="${author.id}">${author.firstName}  ${author.lastName}</option>
                            </#list>
                        </select>

                    </div>
                    <div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-primary ml-3 mt-3">Add Book</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="mt-3">
            <p>
                <a class="btn btn-primary" data-toggle="collapse" href="#searchCollapseExample" role="button"
                   aria-expanded="false"
                   aria-controls="searchCollapseExample">
                    Search book
                </a>
            </p>
            <div class="collapse" id="searchCollapseExample">
                <div class="card card-body">
                    <form method="get" class="form-row" action="/book">
                        <div style="display: contents" class="row">
                            <input type="text" class="form-control col-sm-3" id="search" name="search"
                                   placeholder="Search" value="${search}">
                        </div>
                        <div style="display: contents" class="row">
                            <select class="form-control col-sm-3 ml-3" name="searchBy">
                                <option value="book">By Book Name</option>
                                <option value="author">By Author Name</option>
                            </select>
                        </div>

                        <div>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary ml-3">Search book</button>
                        </div>
                    </form>
                </div>
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
                <th scope="col">View</th>
            </tr>
            </thead>
            <tbody>
            <#list page.getContent() as book>
                <tr>
                    <td>${book.bookName}</td>
                    <td>${book.publishedDate}</td>
                    <td>${book.availableBook}</td>
                    <td>${book.pageCount}</td>
                    <td><a href="/book/getBook/${book.id}">
                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-eye-fill" fill="currentColor"
                                 xmlns="http://www.w3.org/2000/svg">
                                <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"/>
                                <path fill-rule="evenodd"
                                      d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8zm8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7z"/>
                            </svg>
                        </a>
                    </td>
                </tr>
            <#else >
                No Books
            </#list>
            </tbody>
        </table>
    </div>
    <@p.pager url page />
</@c.page>