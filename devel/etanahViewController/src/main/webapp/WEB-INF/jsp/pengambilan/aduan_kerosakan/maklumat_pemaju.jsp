<%-- 

    Author     : wazer
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/fraction.js" language="Javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript">


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.PemohonAduanActionBean">
    <div class="subtitle">
        <s:messages/>
        <s:errors/>
        <s:hidden name="kod" id="kod"/>
        <fieldset class ="aras1">

            <legend>Maklumat Agensi/Pemaju</legend>


            <p>    <label>Pemaju : </label><s:text name="namaPemaju" size="50"/></p>
            <p>
                <label>Kategori  : </label>  <s:select name="kategori" id="kategori" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:option label="Agensi Kerajaaan" value="agensi"/>
                    <s:option label="Syarikat Swasta" value="syarikat"/>
                </s:select>
            </p>
            <p>
                <label><em>*</em>Alamat : </label>
                <s:text name="alamat1" id="alamat1" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2" id="alamat2" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat3" id="alamat3" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Bandar : </label>
                <s:text name="alamat4" id="alamat4" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Poskod : </label>
                <s:text name="poskod" maxlength="5" id="poskod" size="17" onkeyup="validateNumber(this,this.value);" />
                <em>5 Digit [cth : 12000]</em>
            </p>

            <p>
                <label><em>*</em>Negeri : </label>
                <%--penyerahNegeri.kod--%>
                <s:select name="negeri" id="negeri" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
                <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
            </p>
            <!--                <p>
                                <label>No.Telefon</label>
            <s:text name="notel" id="notek" size="15"/>
        </p>
        <p>
            <label>Email</label>
            <s:text name="emel" id="emel" size="50"/>
        </p>-->
            <br/>
            <p align="center">
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p><br>
            <p> 
        </fieldset>

    </div>

    <br>

</s:form>



