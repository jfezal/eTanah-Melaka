<%--
    Document   : utiliti_carian_kompaun
    Created on : Feb 4, 2013, 12:23:22 PM
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

    function refreshPageKompaun1(idPermohonan){
        alert("yuhu");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_status_kompaun?reload&idPermohonan='+idPermohonan;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function refreshPageKompaun(){
        //alert("yuhu1");
        var url = '${pageContext.request.contextPath}/penguatkuasaan/utiliti_status_kompaun?refreshpage';
        $.get(url,
        function(data){
            $("#lampiranDiv").replaceWith($('#lampiranDiv', $(data)));
            //$('#page_div').html(data);
        },'html');
    }


 
</script>

<s:form beanclass="etanah.view.penguatkuasaan.UtilitiStatusKompaunActionBean" name="form1">
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
                    <display:column title="Kompaun (RM)" property="amaun"></display:column>
                    <display:column title="Status Bayaran">
                        <c:if test="${line.resit.idDokumenKewangan eq null}"><font color="red">Belum Dibayar</font></c:if>
                        <c:if test="${line.resit.idDokumenKewangan ne null}">Sudah Dibayar</c:if>
                    </display:column>
                    <display:column title="Jenis Bayaran">
                        <c:if test="${line.statusTerima.kod ne null}">
                            ${line.statusTerima.nama}
                        </c:if>
                        <c:if test="${line.statusTerima.kod eq null}">
                            Bayaran Kompaun
                        </c:if>
                    </display:column>
                    <display:column title="Tarikh Bayar">
                        <c:forEach items="${actionBean.senaraiMohonTuntutBayar}" var="senarai">
                            <c:if test="${senarai.permohonanTuntutanKos.idKos eq line.permohonanTuntutanKos.idKos}">
                                <fmt:formatDate pattern="dd/MM/yyyy hh:mm:ss aaa" value="${senarai.tarikhBayar}"/>
                            </c:if>
                        </c:forEach>
                    </display:column>
                    <display:column title="No.Resit" property="resit.idDokumenKewangan"></display:column>
                </display:table>

                <div id="lampiranDiv">
                    <c:if test="${fn:length(actionBean.senaraiKompaun) ne 0}">
                        <p>
                            <b>Lampiran:</b>
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                                <c:if test="${senarai.dokumen.kodDokumen.kod eq 'RBY'}">
                                    <c:if test="${senarai.dokumen.namaFizikal != null}">
                                        <br>
                                        -
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                             onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                             onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                        <font size="2px;">${senarai.dokumen.tajuk}&nbsp;</font>
                                    </c:if>
                                </c:if>
                                <c:set value="${count+1}" var="count"/>
                            </c:forEach>
                        </p>
                    </c:if>

                </div>




                <table width="70%">
                    <tr><td align="center">
                            <br/>
                            <c:choose>
                                <c:when test="${actionBean.idPermohonanNotExist eq true || fn:length(actionBean.senaraiKompaun) eq 0}">
                                    <s:submit name="simpanMuktamadKompaun" id="save" disabled="true" value="Simpan & Jana" class="longbtn" onclick="return validateForm()"/>
                                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="resetForm();"/>
                                    <s:submit name="cariSemula" value="Cari Semula" class="btn"/>
                                </c:when>

                            </c:choose>

                </table>

            </div>
        </fieldset>
    </div>

</s:form>

