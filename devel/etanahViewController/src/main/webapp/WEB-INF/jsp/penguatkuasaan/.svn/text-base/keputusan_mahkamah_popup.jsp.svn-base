<%-- 
    Document   : keputusan_mahkamah
    Created on : Jan 20, 2010, 11:24:21 AM
    Author     : farah.shafilla
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
            self.opener.refreshPageMahkamah();
            //alert("Maklumat telah berjaya disimpan.");
            self.close();
        },'html');

    }

    function validateForm(){
        if($('#mahkamah').val() == 'Sila Pilih'){
            alert("Sila Pilih Mahkamah");
            $('#mahkamah').focus();
            return false;
        }
        if($('#tarikhSebutan').val() == ''){
            alert("Sila Isi Tarikh Sebutan/Perbicaraan");
            $('#tarikhSebutan').focus();
            return false;
        }
       
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }
 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MahkamahActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Keputusan Mahkamah
            </legend>
            <div class="content">
                <p>
                    <label>Mahkamah Majistret:</label>
                    <s:select name="namaSidang" value="${actionBean.permohonanRujukanLuar.namaSidang}" id="mahkamah">
                        <s:option>Sila Pilih</s:option>
                        <c:if test="${actionBean.kodNegeri.kod eq '05'}">
                            <s:option>Bahau</s:option>
                            <s:option>Jelebu</s:option>
                            <s:option>Kuala Pilah</s:option>
                            <s:option>Port Dickson</s:option>
                            <s:option>Rembau</s:option>
                            <s:option>Seremban</s:option>
                            <s:option>Tampin</s:option>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri.kod eq '04'}">
                            <s:option>Jasin</s:option>
                            <s:option>Alor Gajah</s:option>
                        </c:if>
                    </s:select>&nbsp;

                </p>
                <p>
                    <label>No Rujukan :</label>
                    <s:text name="noRujukan" onkeyup="this.value=this.value.toUpperCase();"/>&nbsp;
                </p>
                <p>
                    <label>Tarikh Sebutan/Perbicaraan :</label>
                    <s:text name="tarikhRujukan" id="tarikhSebutan" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" />
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Tarikh Keputusan :</label>
                    <s:text name="tarikhSidang" class="datepicker" formatType="date" formatPattern="dd/MM/yyyy" id="trhResult"/>
                    <font color="red" size="1">cth : hh / bb / tttt</font>&nbsp;
                </p>
                <p>
                    <label>Ringkasan Keputusan :</label>
                    <s:textarea name="catatan" rows="5" cols="50" onkeyup="this.value=this.value.toUpperCase();" />&nbsp;
                </p>

            </div>

            <p align="right">
                <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                <%--<s:submit name="simpanPopup" id="simpan" value="Simpan" class="btn" onclick="if(validateForm())save(this.name,this.form);"/>--%>
                <s:button class="btn" value="Simpan" name="simpanPopup"  onclick="if(validateForm())save(this.name,this.form);" />
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>
    </div>
</s:form>

