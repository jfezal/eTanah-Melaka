<%--
 Document  : notis_penyampaian
 Created on : Feb 10, 2011, 3:23:34 AM
 Author  :
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function validate(){

        var bil = $(".rowCount").length;
        //alert("bil"+bil);
        for (var i = 0; i < bil; i++){
            //alert("lalala");

            var nama = document.getElementById('namela'+i);
            var kod = document.getElementById('kodPenyampaian'+i);
            var kod2 = document.getElementById('kodPenghantaran'+i);
            var tarikh = document.getElementById('tarikhDihantar'+i);
            var tarikh2 = document.getElementById('tarikhTerima'+i);
            var catat = document.getElementById('catatanTerima'+i);
            if(nama.value == "" ){
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#namela'+i).focus();
                return false;
            }
            if(kod.value == "" ){
                alert("Sila Pilih Status Penyampaian");
                $('#kodPenyampaian'+i).focus();
                return false;
            }
            if(kod2.value == "" ){
                alert("Sila Pilih Cara Penghantaran");
                $('#kodPenghantaran'+i).focus();
                return false;
            }
            if(tarikh.value == "" ){
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhDihantar'+i).focus();
                return false;
            }
            if(tarikh2.value == "" ){
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerima'+i).focus();
                return false;
            }
            if(catat.value == "" ){
                alert("Sila Masukkan Catatan");
                $('#catatanTerima'+i).focus();
                return false;
            }
        }

        return true;
    <%-- alert("start simpan");
     var bilangan = $(".rowCount").length;
     alert("bilangan la : "+bilangan);
     return true;--%>
         }
         function muatNaikForm(notis) {
             var left = (screen.width/2)-(1000/2);
             var top = (screen.height/2)-(150/2);
             var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_penyampaian?popupUpload&idNotis='+notis;
             window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
             // window.location.reload();
         }

         function doViewReport(v) {
             var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
             window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
         }

         function scan(notis) {
             var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_penyampaian?popupScan&idNotis='+notis;
             window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
         }

         function findNoKadPengenalan(idPenghantarNotis,index){
             //alert("id : "+ idPenghantarNotis);
             //alert("index : "+index);

             if(idPenghantarNotis != ""){
                 $.get('${pageContext.request.contextPath}/penguatkuasaan/notis_penyampaian?findNoPengenalan&idPenghantarNotis='+idPenghantarNotis+'&index='+index,
                 function(data){
                     //alert(data);
                     $('#icLoad'+index).empty();
                     $('#icLoad'+index).append("No.KP:("+data+")");
                 }, 'html');

             }

         }

         function addPenghantarNotis(){
             window.open("${pageContext.request.contextPath}/penguatkuasaan/notis_penyampaian?popupPenghantarNotis", "eTanah",
             "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
         }

    <%--  $.ajax({
    url : url,
    dataType : 'html',
    success : function(data) {$('#icNo').html(data);doUnBlockUI();}
    });--%>


</script>

<s:form beanclass="etanah.view.penguatkuasaan.NotisPenyampaianActionBean" id="folder_div">
    <s:hidden name="penghantarNotisList" id="penghantarNotisList" />
    <s:messages/>
    <s:errors/>&nbsp;
    <div class="subtitle" id="myDiv">
        <fieldset class="aras1">
            <legend>
                Maklumat Penghantaran Notis Siasatan
            </legend><br>

            <p class=instr>
                *<em>Petunjuk :</em>
            </p>
            <p class=instr>
                <em>H:</em> Tarikh Hantar
                <em>T:</em> Tarikh Terima
            </p>
            <font size="2" color="black"></font>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="20" height="20" /> - <font size="1" color="black">Papar Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                     width="20" height="20" /> - <font size="1" color="black">Muat Naik Dokumen</font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                     width="20" height="20" /> - <font size="1" color="black">Imbas Dokumen</font>
            </p><br>

            <div class="content" align="center">
                <%--lalala : display kt sini : <div id="icNo"></div> ic : <s:text name="icNo" id="icNo"/>--%>
                <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/penguatkuasaan/notis_penyampaian" id="line">
                    <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Nama" class="rowCount">
                        ${line.pihak.pihak.nama}<br>
                        (
                        <c:if test="${line.pihak.jenis.kod ne 'PG'}">
                            Penggadai
                        </c:if>
                        <c:if test="${line.pihak.jenis.kod eq 'PG'}">
                            ${line.pihak.jenis.nama}
                        </c:if>)
                    </display:column>
                    <display:column title="Nama Penghantar Notis" >
                        <s:select name="namaPengahantar${line_rowNum-1}" id="namela${line_rowNum-1}" onchange="findNoKadPengenalan(this.value,'${line_rowNum-1}');" value="${line.penghantarNotis.idPenghantarNotis}" style="width:180px;">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                        </s:select> <br>
                        <div id="icLoad${line_rowNum-1}">No.K/P:(${line.penghantarNotis.noPengenalan})</div>
                        <%--<div id="ic">
                        </div>
                        <div id="ic2">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.noKadList}" var="line2">
                            <c:if test="${line_rowNum eq count}">
                                <c:if test="${line2 ne null && line2 ne ''}">
                                ic : (${line2})
                                </c:if>
                            </c:if>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>

                        </div>--%>

                        &nbsp; <br>
                        <img src="${pageContext.request.contextPath}/pub/images/tambah.png" height="15" width="15" alt="Tambah Penghantar Notis"
                             onmouseover="this.style.cursor='pointer';" onclick="addPenghantarNotis();return false;" title="Tambah Penghantar Notis"/>

                    </display:column>

                    <display:column title="Status Penyampaian" class="${line_rowNum}">
                        <s:select name="kodStatusTerima${line_rowNum-1}" id="kodPenyampaian${line_rowNum-1}" value="${line.kodStatusTerima.kod}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                        </s:select>
                    </display:column>

                    <display:column title="Cara Penghantaran" class="${line_rowNum}">
                        <s:select name="kodCaraPenghantaran${line_rowNum-1}" id="kodPenghantaran${line_rowNum-1}" value="${line.kodCaraPenghantaran.kod}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                        </s:select>
                    </display:column>

                    <display:column title="Tarikh" class="${line_rowNum}">
                        <p>H : <s:text class="datepicker" id="tarikhDihantar${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhHantar" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                            <br>
                            &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                        <p>T : <s:text class="datepicker" id="tarikhTerima${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhTerima" formatPattern="dd/MM/yyyy" style="width:100px;"/>
                            <br>
                            &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                        </p>
                    </display:column>
                    <display:column title="Catatan" class="${line_rowNum}" >
                        <s:textarea name="listNotis[${line_rowNum-1}].catatanPenerimaan" id="catatanTerima${line_rowNum-1}" rows="2" cols="15" onblur="this.value=this.value.toUpperCase();" />
                    </display:column>

                    <display:column title="Tindakan">
                        <p align="center">
                            <c:if test="${line.buktiPenerimaan.namaFizikal == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <c:if test="${line.buktiPenerimaan.namaFizikal != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                        </p>
                    </display:column>
                </display:table>
            </div>
            <div>
                <p align="right">
                    <%--  <c:if test="${actionBean.button ne true}">--%>
                    <s:button class="btn" name="simpan" value="Kemaskini" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    <%--</c:if>--%>
                </p>
            </div>
            <br>
        </fieldset>
    </div>
</s:form>