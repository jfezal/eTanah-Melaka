<%-- 
    Document   : change_password
    Created on : Apr 12, 2010, 5:27:17 PM
    Author     : solahuddin
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/libs/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/source/digitalspaghetti.password.js"></script>

<script type="text/javascript">

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function test(f) {
        $(f).clearForm();
    }

</script>
<s:form beanclass="etanah.view.uam.ChangePasswordActionBean">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tukar Kata Laluan</legend>
<!--            <p>
                <label><font color="red">*</font>Id Pengguna :</label>
               
            </p>
            <p>
                <label><font color="red">*</font>Kata Laluan Lama :</label>
                <s:password name="lKataLaluan" value="${actionBean.lKataLaluan}"/>
            </p>
            <p>
                <label><font color="red">*</font>Kata Laluan Baru :</label>
                <s:password name="password" id="password" value="${actionBean.pguna.password}"/>

            </p>
            <p>
                <label><font color="red">*</font>Pengesahan Kata Laluan Baru :</label>
                <s:password name="pKataLaluan" value="${actionBean.pKataLaluan}"/>
            </p>-->

            <table>
                <tr>
                    <td>
                        <label><font color="red">*</font>Id Pengguna :</label>
                    </td>
<!--                    <td>:</td>-->
                    <td>
                          ${actionBean.idPengguna}
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><font color="red">*</font>Kata Laluan Lama :</label>
                    </td>
<!--                    <td>:</td>-->
                    <td>
                        <input type="password" name="lKataLaluan" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><font color="red">*</font>Kata Laluan Baru :</label>
                    </td>
<!--                    <td>:</td>-->
                    <td>
                        <input type="password" id="password" name="password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label><font color="red">*</font>Pengesahan Kata Laluan Baru :</label>
                    </td>
<!--                    <td>:</td>-->
                    <td>
                        <input type="password" name="pKataLaluan" />
                    </td>
                </tr>
            </table>
        </fieldset>
        <p>
            <label>&nbsp;</label>
            <s:submit name="change" id="save" value="Tukar" class="btn"/>
            <s:submit name="showForm" value="Isi Semula" class="btn" onclick="test(this.form);"/>
        </p>
    </div>

<!--
    <div style="position:relative; margin: 0 auto; width: 50%; top: 25%">
        <h2>Demo</h2>
        <table>
            <tr>
                <td>
                    <label for="username">Username</label>
                </td>
                <td>
                    <input type="text" id="username" name="username" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="password">Password</label>
                </td>
                <td>
                    <input type="password" id="password" name="password" />
                </td>
            </tr>
        </table>
    </div>-->
    <script type="text/javascript">
        jQuery('#password').pstrength({'debug': true});
        jQuery('#password').pstrength.addRule('twelvechar', function (word, score) {
            return word.length >= 12 && score;
        }, 3, true);
    </script>
</s:form>
