<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>--%>
<%--<%@page contentType="text/html" pageEncoding="windows-1252" language="java"%>--%>
<link type="text/css" href="${pageContext.request.contextPath}/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ui.datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/libs/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/passwordChecking/source/digitalspaghetti.password.js"></script>

<script type="text/javascript">
    //    $(document).ready(function(){
    //        var obj = getparamURL('error');
    //        $.post('${pageContext.request.contextPath}/?abc&ddd='+obj),
    //        function(data){
    //            $("#page_div").html(data);
    //        }
    //    });
    //    function getparamURL( name )
    //    {
    //      name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    //      var regexS = "[\\?&]"+name+"=([^&#]*)";
    //      var regex = new RegExp( regexS );
    //      var results = regex.exec( window.location.href );
    //      if( results == null )
    //        return "";
    //      else
    //        return results[1];
    //    }
    function test(f) {
        $(f).clearForm();
    }
    
    function validateForm(f) {

        if(f.elements['ktLama'].value == '') {
            alert('Sila masukkan Katalaluan Lama.');
            f.elements['ktLama'].focus();
            return false;
        }
        else if(f.elements['ktBaru'].value == ''){
            alert('Sila masukkan Katalaluan Baru');
            f.elements['ktBaru'].focus();
            return false;
        }
        else if(f.elements['pktBaru'].value == ''){
            alert('Sila masukkan Pengesahan Katalaluan Baru');
            f.elements['pktBaru'].focus();
            return false;
        }
        else return true;
    }
    
    function passwordCheck(){
        
        var re = /^\w*(?=\w*\d)(?=\w*[a-z])(?=\w*[A-Z])\w*$/
        if (!re.test(myString)) { alert("Please enter a valid password!"); }
    }
</script>

<s:form beanclass="etanah.view.uam.PengesahanKataLaluan" id="pengesahan">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Penukaran Katalaluan</legend>
            <font size="1" color="Red">Sila masukkan data Pengguna.</font>
            <table>

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


            <s:submit name="pengesahan" id="Simpan" value="Simpan" class="btn" onclick="return validateForm(this.form)"/>
            <s:submit name="kembali" value="Kembali" class="btn"/>
        </p>
    </div>
    <script type="text/javascript">
        jQuery('#password').pstrength({'debug': true});
        jQuery('#password').pstrength.addRule('twelvechar', function (word, score) {
            return word.length >= 12 && score;
        }, 3, true);
    </script>
</s:form>
