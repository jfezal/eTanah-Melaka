<%--
    Document   : popup_kemaskini_keputusan : for MELAKA sek 422,423,424,427,428,429
    Created on : Created on : Sep 22, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageKemaskiniKeputusan();
            //alert("Maklumat telah berjaya disimpan.");
            self.close();
        },'html');

    }

    function validateForm(){
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }

    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatMahkamahActionBean">
    <s:messages />

    <%--for sek 422,423,424,427,428 & 429 melaka : kemaskini maklumat keputusan mahkamah dan tarikh keputusan--%>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mahkamah
            </legend>
            <div class="content">
                <p>
                    <label>Kategori Mahkamah :</label>
                    ${actionBean.permohonanRujukanLuar.agensi.nama}
                </p>
                <p>
                    <label>Tempat Mahkamah :</label>
                    ${actionBean.namaSidang}&nbsp;
                </p>
                <p>
                    <label>No Rujukan :</label>
                    ${actionBean.noRujukan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Sebutan/Perbicaraan :</label>
                    ${actionBean.tarikhSidang}&nbsp;
                </p>
                <p>
                    <label>Status :</label>
                    ${actionBean.permohonanRujukanLuar.keputusanPendakwaan.nama}&nbsp;
                </p>
                <p>
                    <label>Minit :</label>
                    ${actionBean.catatan}&nbsp;
                </p>
                <p>
                    <label>Tarikh Keputusan :</label>
                    <s:text name="tarikhRujukan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="tarikhRujukan"/>
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Keputusan Mahkamah :</label>
                    <s:textarea name="keputusanMahkamah" rows="5" cols="50" onchange="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
            </div>
            <br/><br/>

        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pihak Mahkamah
            </legend>
            <br/>

            <div class="content">
                <fieldset class="aras2">
                    <legend>
                        Pihak Mahkamah
                    </legend>
                    <div align="center" >
                        <display:table name="${actionBean.senaraipermohonanRujukanLuarPeranan}" id="line" class="tablecloth" >
                            <display:column title="Bil">
                                ${line_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama" />
                            <display:column title="Peranan" property="kodPerananRujukanLuar.nama"/>
                        </display:table>
                    </div>
                    <p>&nbsp;</p>
                </fieldset>
                <br/>
                <p align="right">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idRujukan"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" value="Simpan" name="kemaskiniRekodKeputusan"  onclick="if(validateForm())save(this.name,this.form);" />
                    <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>

