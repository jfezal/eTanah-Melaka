<%--
    Document   : utiliti_kompaun
    Created on : June 20, 2012, 12:23:22 PM
    Author     : latifah.iskak
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>



<script language="javascript" type="text/javascript">
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


    function validateForm(){

        if(document.getElementById("idPermohonanCarian").value == ""){
            alert("Sila cari dahulu rekod dengan memasukkan Id Permohonan yang dikehendaki");
            $('#idMohon').focus();
            return false;
        }

        var bil =  ${fn:length(actionBean.senaraiKompaun)};
        var j = 0;
        for (var i = 0; i < bil; i++){

            var amaunBaru = document.getElementById('amaunBaru'+i).value;
            var amaunLama = document.getElementById('amaunLama'+i).value;
            var tempohHari = document.getElementById('tempohHari'+i).value;

            if(amaunBaru != "" && amaunLama != ""){
                if(parseFloat(amaunBaru) > parseFloat(amaunLama)){
                    alert("Nilai amaun baru mestilah lebih kecil dari amaun lama");
                    $('#amaunBaru'+i).focus();
                    return false;
                }
            }

            if(tempohHari == "" && amaunBaru != ""){
                alert("Sila masukkan tempoh hari");
                $('#tempohHari'+i).focus();
                return false;
            }

            if(tempohHari != "" && amaunBaru == ""){
                alert("Sila masukkan amaun baru");
                $('#tempohHari'+i).focus();
                return false;
            }
            
            if(tempohHari != "" && amaunBaru != ""){
                j++;
            }
        }

        if(j == 0 ){
            alert("Sila masukkan maklumat amaun dan tempoh hari. ");
            return false;
        }

        return true;
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function resetForm() {
        if(document.getElementById("idMohon").value !=""){
            document.getElementById("idMohon").value ="";
        }

        var bil =  ${fn:length(actionBean.senaraiKompaun)};
        for (var i = 0; i < bil; i++){
            var amaunBaru = document.getElementById('amaunBaru'+i).value;
            var tempohHari = document.getElementById('tempohHari'+i).value;

            if(amaunBaru != ""){
                document.getElementById('amaunBaru'+i).value ="";
            }
            if(tempohHari != ""){
                document.getElementById('tempohHari'+i).value ="";
            }

        }

    }

    function muatNaikForm(folderId, idPermohonan, dokumenKod) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function removeImej(idImej) {
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?deleteSelected&idImej='+idImej;
            $.get(url,
            function(data){
                $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
            },'html');
        }
    }

    function refreshPageKompaun1(idPermohonan){
        alert("yuhu");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?reload&idPermohonan='+idPermohonan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageKompaun(){
        //alert("yuhu1");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_pengurangan_kompaun?refreshpage';
        $.get(url,
        function(data){
            $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
            //$('#page_div').html(data);
        },'html');
    }
    
    function totalAmountKompaun(){

        var total = 0;
        var bil = ${fn:length(actionBean.senaraiKompaun)};
        //        alert("bil totalAmountKompaunOks : "+bil);
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('amaunBaru'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
                document.getElementById('amaunBaru'+i).value= parseFloat(amount).toFixed(2);
                //                document.getElementById('jumCaraBayar').value=parseFloat(total).toFixed(2);
            }
        }
    }


 
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiKompaunActionBean" name="form1">
    <s:messages/>
    <s:errors/>
    <s:hidden name="permohonan.idPermohonan" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Carian kompaun</legend>
            <br>
            <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                <input type="text" name="idPermohonan" id="idMohon" onkeyup="this.value=this.value.toUpperCase();"/>
                <s:submit name="searchKompaun" value="Cari" class="btn"/>
            </p>



            <p>
                <label>Id Permohonan :</label>
                ${actionBean.idPermohonan} &nbsp;
                <s:hidden name="idPermohonan" id="idPermohonanCarian"/>
            </p>


            <div class="content" align="center">

                <br/>
                <c:if test="${!actionBean.newAmaunUpdated}">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaun}" cellpadding="0" cellspacing="0" id="line" >
                        <display:column title="Bil" sortable="true">
                            ${line_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewSyorKompaun('${line.idKompaun}','${line.orangKenaSyak.idOrangKenaSyak}')">${line.noRujukan}</a>
                            <input name="idKompaun${line_rowNum-1}" id="idKompaun${line_rowNum-1}" value="${line.idKompaun}" type="hidden"/>
                        </display:column>
                        <display:column title="Diserahkan Kepada">${line.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${line.noPengenalan ne null}">
                                ${line.noPengenalan}
                            </c:if>
                            <c:if test="${line.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>
                        <display:column title="Amaun Lama (RM)">
                            ${line.amaun}
                            <input type="hidden" name="amaunLama${line_rowNum-1}" id="amaunLama${line_rowNum-1}" value="${line.amaun}"/>
                        </display:column>
                        <display:column title="Amaun Baru (RM)">
                            <input name="amaunBaru${line_rowNum-1}" id="amaunBaru${line_rowNum-1}" size="10" maxlength="10" onkeyup="validateNumber(this,this.value);" onblur="totalAmountKompaun();"/>
                            <input type="hidden" name="idOks${line_rowNum-1}" id="idOks${line_rowNum-1}" value="${line.orangKenaSyak.idOrangKenaSyak}"/>
                        </display:column>
                        <display:column title="Tempoh (Hari)">
                            <input name="tempohHari${line_rowNum-1}" id="tempohHari${line_rowNum-1}" size="3" maxlength="3" onkeyup="validateNumber(this,this.value);" />
                        </display:column>
                        <%--<display:column title="Kod Status" property="statusTerima.kod"/>--%>
                    </display:table>
                </c:if>


                <c:if test="${actionBean.newAmaunUpdated}">
                    <display:table class="tablecloth" name="${actionBean.senaraiKompaunUpdate}" cellpadding="0" cellspacing="0" id="k" >
                        <display:column title="Bil" sortable="true">
                            ${k_rowNum}
                        </display:column>
                        <display:column title="No.Siri">
                            <a class="popup" onclick="viewSyorKompaun('${k.idKompaun}','${k.orangKenaSyak.idOrangKenaSyak}')">${k.noRujukan}</a>
                            <input name="idKompaun${k_rowNum-1}" id="idKompaun${k_rowNum-1}" value="${k.idKompaun}" type="hidden"/>
                        </display:column>
                        <display:column title="Diserahkan Kepada">${k.isuKepada}</display:column>
                        <display:column title="No.KP">
                            <c:if test="${k.noPengenalan ne null}">
                                ${k.noPengenalan}
                            </c:if>
                            <c:if test="${k.noPengenalan eq null}">
                                Tiada data
                            </c:if>
                        </display:column>
                        <display:column title="Amaun Baru (RM)" property="amaun" format="{0,number, 0.00}"></display:column>
                        <%--<display:column title="Kod Status" property="statusTerima.kod"/>--%>
                    </display:table>
                </c:if>

                <%-- <div id="lampiranDiv">
                     <c:if test="${fn:length(actionBean.senaraiKompaun) ne 0 || fn:length(actionBean.senaraiKompaunUpdate) ne 0}">
                         <p>
                             <b>Lampiran:</b>
                             <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                                  onclick="muatNaikForm('${actionBean.permohonan.folderDokumen.folderId}',
                                      '${actionBean.permohonan.idPermohonan}','SSPK');return false;" height="30" width="30" alt="Muat Naik"
                                  onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>
                             <c:set value="1" var="count"/>
                             <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                 <c:if test="${senarai.dokumen.kodDokumen.kod eq 'SSPK'}">
                                     <c:if test="${senarai.dokumen.namaFizikal != null}">
                                         <br>
                                         -
                                         <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                              onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                              onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                         <font size="2px;">${senarai.dokumen.tajuk}&nbsp;</font>/
                                         <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                              onclick="removeImej('${senarai.dokumen.idDokumen}','');" height="15" width="15" alt="Hapus"
                                              onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>
                                     </c:if>
                                 </c:if>
                                 <c:set value="${count+1}" var="count"/>
                             </c:forEach>
                         </p>
                     </c:if>

                </div> --%>




                <table width="70%">
                    <tr><td align="center">
                            <br/>
                            <c:choose>
                                <c:when test="${actionBean.idPermohonanNotExist eq true || fn:length(actionBean.senaraiKompaun) eq 0}">
                                    <s:submit name="simpanMuktamadKompaun" id="save" disabled="true" value="Simpan" class="longbtn" onclick="return validateForm()"/>
                                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="resetForm();"/>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${!actionBean.newAmaunUpdated}">
                                        <s:submit name="simpanMuktamadKompaun" id="save" value="Simpan" class="longbtn" onclick="return validateForm()"/>
                                        <s:button name="reset" value="Isi Semula" class="btn" onclick="resetForm();"/>
                                    </c:if>
                                    <c:if test="${actionBean.newAmaunUpdated}">
                                        <s:submit name="cariSemula" value="Cari Semula" class="btn"/>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>

                </table>

            </div>
        </fieldset>
    </div>

</s:form>

