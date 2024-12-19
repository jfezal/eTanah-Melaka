<%-- 
    Document   : firstTime_login_1
    Created on : Oct 11, 2011, 12:48:12 PM
    Author     : amir.muhaimin
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<script type="text/javascript">
    function validateForm(f) {

        if(f.elements['pguna.nama'].value == '') {
            alert('Sila masukkan Nama.');
            f.elements['pguna.nama'].focus();
            return false;
            
        } else if(f.elements['pguna.katalaluanLama'].value == '') {
            alert('Sila masukkan Katalaluan Lama.');
            f.elements['pguna.katalaluanLama'].focus();
            return false;
        }
        else return true;
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="" name="userForm" id="">
    <div class="subtitle">
        <s:errors/>
        <s:messages/>
    </div>
    <div class="subtitle" style="">
        <fieldset class="aras1">
            <legend>Log Masuk Pertama Kali</legend>
            <font size="1" color="Red"><em>Sila masukkan data Pengguna untuk membuat pendaftaran pertama kali.</em></font>
            <p>
                <label><font color="red">*</font>Nama :</label>
            <s:text name="" style="width:250px" onblur="this.value=this.value.toUpperCase();" id="nama"/>
            </p>
            <p>
                <label><font color="red">*</font>Katalaluan lama :</label>
            <s:text name="" id="klLama"/>
            </p>
            <br>
            <p>
                <label><font color="red">*</font>Katalaluan baru :</label>
            <s:text name="" id="klBaru"/>
            </p>
            <p>
                <label><font color="red">*</font>Pengesahan Katalaluan baru :</label>
            <s:text name="" id="pKl"/>
            </p>
            <br>
            <p>
                <label>&nbsp;</label>

            <c:if test="${simpan}">
                <s:submit name="" id="save" value="Simpan" class="btn" onclick="return validateForm(this.form)"/>
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearText('user_uam');"/>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </c:if>

            </p>
        </fieldset>
    </div>
</s:form>
