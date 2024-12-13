<%-- 
    Document   : tambah_pemohonV2
    Created on : Jun 13, 2013, 10:53:45 AM
    Author     : afham
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Maklumat Pemohon</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ageCalculator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">

    function savePenjaga(event, f, id) {
        if (validation()) {
            alert("simpan1");
        } else {
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url, q,
                    function(data) {
                        $('#page_div', opener.document).html(data);
                        self.refreshPagePBT();
                        self.close();
                    }, 'html');

        }
    }

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="list" />
<s:form beanclass="etanah.view.stripes.pelupusan.utility.UtilitiSalinanActionBean" name="editsalinan">
    <s:errors/>
    <s:messages/>

    <div  id="hakmilik_details">
        <s:hidden name="hakmilikPerbicaraan.idPerbicaraan" />
        <div class="subtitle" id="main">
            <fieldset class="aras1">
                <div id="maklumatpendeposit">
                    <legend>Tambah Salinan Kepada</legend>
                    <div class="subtitle">
                        <fieldset class="aras1">
                            <div class="content" >
                                <p>
                                <table align="center">

                                    <tr>
                                        <td><label>Nama :</label></td>
                                        <td><s:text name="SalinanKepada.nama" size="40" id="nama" onkeyup="this.value=this.value.toUpperCase();"/><font color="red">*</font></td>
                                    </tr>
                                    <tr>
                                        <td><label>Alamat Berdaftar :</label></td>
                                        <td><s:text name="SalinanKepada.alamat1" id="alamat1" size="40" maxlength="40"/><font color="red">*</font></td>
                                    </tr>
                                    <tr>
                                        <td><b>&nbsp;</b></td>
                                        <td><s:text name="SalinanKepada.alamat2" id="alamat2" size="40" maxlength="40"/><font color="red">*</font></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td><s:text name="SalinanKepada.alamat3" id="alamat3" size="40" maxlength="40"/><font color="red">*</font></td>

                                    </tr>
                                    <tr>
                                        <td><label>Bandar :</label></td>
                                        <td><s:text name="SalinanKepada.alamat4" id="bandar" size="40" maxlength="25"/><font color="red">*</font></td>

                                    </tr>
                                    <tr>
                                        <td><label>Poskod :</label></td>
                                        <td><s:text name="SalinanKepada.poskod" id="poskod" size="40" maxlength="5" onkeyup="validateNumber(this,this.value);"/><font color="red">*</font></td>

                                    </tr>

                                </table>  
                                <br>
                                <center>
                                
                                <td><s:button name="simpanKehadiran" id="simpan" value="Simpan" class="btn" onclick="savePenjaga(this.name, this.form, '${SalinanKepada}');"/></td>
                                <s:button name="editSalinan" id="123" value="Tambah Pemohon" class="longbtn" onclick="savePenjaga();"/>
                                
                                </center>
                            </div>
                        </fieldset>
                    </div>
                    <br/>
                </div>
            </fieldset>
        </div>
        <br><br>
    </div>
</s:form>
</body>
