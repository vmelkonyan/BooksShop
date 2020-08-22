<#import "../parts/common.ftlh" as c>
<#import "../parts/login_p.ftlh" as l>
<#include "../parts/security.ftlh">
<#import "../parts/page.ftlh" as p>
<@c.page>
    <div class="card  mt-8">
        <div class="card-body">
            <h5 class="card-title"><label>BookName Name :</label> ${book.bookName}</h5>
            <h5 class="card-title"><label>Page Count : </label>${book.pageCount}</h5>
            <h5 class="card-title"><label>Published Date : </label>${book.publishedDate}</h5>
            <h5 class="card-title"><label>Book available : </label> ${book.availableBook}</h5>
            <h5 class="card-title"><label>Auhtors : </label></h5>
            <#list book.getAuthorSet() as author>
                <p><b>Name</b> ${author.firstName} ${author.lastName}: <b>Written books</b> ${author.bookWrittenCount}
                </p>
            </#list>
            <a href="../../book/buyBook/${book.id}" class="btn btn-primary">Buy book</a>
        </div>
    </div>
</@c.page>