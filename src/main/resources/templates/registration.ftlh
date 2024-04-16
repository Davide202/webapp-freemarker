<#import "/spring.ftl" as spring/>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Boot Form Submission</title>
</head>
<body>
<#include "script.ftlh" />
    <h1>Spring Boot Form Submission</h1>

    <br/><br/>

    <#if regioni??>
        <div class="dropdown">
                <#if user.regione??>
                    <button class="dropbtn">${user.regione}</button>
                <#else>
                    <button class="dropbtn">Regioni</button>
                </#if>
                <div class="dropdown-content">
                    <#list regioni as key >
                        <a href="/registration/regione/${key}">${key}</a>
                    </#list>
                </div>
            </div>
    </#if>

    <#if provincie??>
        <div class="dropdown">
                <#if user.provincia??>
                    <button class="dropbtn">${user.provincia}</button>
                <#else>
                    <button class="dropbtn">Provincie</button>
                </#if>
                <div class="dropdown-content">
                    <#list provincie as provincia >
                        <a href="/registration/provincia/${provincia}">${provincia}</a>
                    </#list>
                </div>
            </div>
    </#if>



    <#if comuni??>
        <div class="dropdown">
                <#if user.comune??>
                    <button class="dropbtn">${user.comune}</button>
                <#else>
                    <button class="dropbtn">Comuni</button>
                </#if>
                <div class="dropdown-content">
                    <#list comuni as comune >
                        <a href="/registration/comune/${comune}">${comune}</a>
                    </#list>
                </div>
            </div>
    </#if>


    <br/><br/>

    <form action="/registration" method="post">

        <div>Name:</div>
        <input id="name" type="text" name="name"
            onchange="liveSearch(this.value);"
            placeholder="<#if user?? && user.name??>${user.name}<#else>name</#if>"
        />
        <br/><br/>
        <div>Surname:</div>
        <input type="text" name="surname"
            onchange="printOnConsole(this);"
            placeholder="<#if user?? && user.surname??>${user.surname}<#else>surname</#if>"
        />
        <br/><br/>
        <div>Message:</div>
        <textarea rows="2" name="message"
            onchange="printOnConsole(this);"
            placeholder="<#if user?? && user.message??>${user.message}<#else>message</#if>"
        ></textarea>
        <br/><br/>
        <input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/>

    </form>

    <br>
    <button value="ciao" onclick="printOnConsole(this);">click me</button>

</script>
</body>
</html>