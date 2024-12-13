<%-- 
    Document   : tambah_maklumat_ahli_lembaga_pengarah
    Created on : May 20, 2010, 3:49:47 PM
    Author     : sitifariza.hanim
    Modified By: Rohan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
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
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
    
    $(document).ready( function(){
        maximizeWindow();
    <c:if test="${!flag}">
            opener.refreshMaklumatPemohon();
            self.close();
    </c:if>

        });
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
        function savePemohonhliLembaga(event, f){
            //alert("savePemohonhliLembaga");
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
                self.close();
            },'html');
        }
         function changeHideSuku(value){
             if(value == "S"){
                 $('#suku').hide();
             }
             else{
                 $('#suku').show();
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
    
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle" id="caw">
    <s:form beanclass="etanah.view.stripes.pelupusan.MaklumatPemohon1ActionBean">
        <div align="center">
            <s:errors/>
            <s:messages/>
        </div>
        <fieldset class="aras1">
            <s:hidden name="pihak1.idPihak"/>
            <legend>Kemasukan Maklumat Ahli Lembaga Pengarah</legend>
            <br/>
            <p>
                <label><font color="red">*</font>Nama Ahli:</label>
                <s:text name="pihakPengarah.nama" size="40" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:hidden name="pihakPengarah.idPengarah"/>
            </p>

            <p>
                <label for="Jenis Pengenalan"> <font color="red">*</font>Jenis Pengenalan :</label>
                <s:select name="pihakPengarah.jenisPengenalan.kod" id="jenisPengenalan"  onchange="javaScript:changeHideSuku(this.options[selectedIndex].text)">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiJenisPengenalanPelupusanLembagaPengarah}" label="nama" value="kod"/>
                </s:select>
            </p>

            <p>
                <label><font color="red">*</font>Nombor Pengenalan :</label>
                <s:text name="pihakPengarah.noPengenalan" size="40"/>

            </p>
            <p>
                <label for="alamat"><font color="red">*</font>Alamat :</label>
                <s:text name="pihakPengarah.alamat1" size="40" id="alamat1" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihakPengarah.alamat2" size="40" id="alamat2" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label> &nbsp;</label>
                <s:text name="pihakPengarah.alamat3" size="40" id="alamat3" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label> &nbsp;</label>
                <s:text name="pihakPengarah.alamat4" size="40" id="alamat4" onkeyup="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label><font color="red">*</font>Poskod :</label>
                <s:text name="pihakPengarah.poskod" size="40" maxlength="5" id="poskod" onkeyup="validateNumber(this,this.value);"/>
            </p>

            <p>
                <label for="Negeri"><font color="red">*</font>Negeri :</label>
                <s:select name="pihakPengarah.kodNegeri.kod" id="negeri">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodNegeri}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label>Jumlah Saham  :</label>
                <s:text name="pihakPengarah.jumlahSaham"  maxlength="3" value="${actionBean.pihakPengarah.jumlahSaham}" size="10" onkeyup="validateNumber(this,this.value);"/>
                <b>Peratus (%)</b>
            </p>

            <p><br/>
                <label>&nbsp;</label>
                <s:submit name="simpanMaklumatAhliLembaga" id="simpan" value="Simpan" class="btn"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
            <br>
        </fieldset>
    </s:form>
</div>


