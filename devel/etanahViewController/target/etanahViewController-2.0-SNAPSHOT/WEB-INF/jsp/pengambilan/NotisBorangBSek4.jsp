<%-- 
    Document   : NotisBorangBSek4
    Created on : 11-May-2011, 15:40:08
    Author     : nordiyana
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);

            setTimeout(function(){
                self.close();
            }, 100);
        },'html');
    }

    function validate(){

        var bil = $(".rowCount").length;

        for (var i = 0; i < bil; i++){

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
    }


    function muatNaikForm(notis) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/notis_borangB?popupUpload&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function addPenghantarNotis(){
        window.open("${pageContext.request.contextPath}/pengambilan/notis_borangB?popupPenghantarNotis", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function scan(notis) {
        var url = '${pageContext.request.contextPath}/pengambilan/notis_borangB?popupScan&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }
     function ajaxLink(link, update) {
        $.get(link, function(data) {
            $(update).html(data);
            $(update).show();
        });
        return false;
    }


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.NotisBorangBActionBean" id="folder_div">

    <s:messages/>
    <s:errors/>&nbsp;
    <c:if test="${actionBean.show ne true}">
        <div  id="hakmilik_details">
         <fieldset class="aras1">
              <legend>
                    Maklumat Hakmilik
                </legend><br/>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                               requestURI="pengambilan/notis_borangB" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>
                    <display:column title="Hakmilik">
                         <s:link beanclass="etanah.view.stripes.pengambilan.NotisBorangBActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this,'#hakmilik_details');" >
                               <%-- <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>--%>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                ${line.hakmilik.idHakmilik}
                            </s:link>
                    </display:column>/>
                    <%--<display:column property="hakmilik.noLot" title="No Lot" />--%>
                    <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                    <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                    <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                   <%-- <display:column title="Tuan Tanah" >
                        <c:forEach items="${actionBean.senaraiPermohonanPihak}" var="senarai">
                               <s:link beanclass="etanah.view.stripes.pengambilan.NotisBorangBActionBean"
                                    event="pihakDetails" onclick="return ajaxLink(this,'#hakmilik_details');" >
                                <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                ${senarai.pihak.nama}
                            </s:link><br/>
                        </c:forEach>
                       
                    </display:column>--%>
                </display:table>
            </div><br /><br />
        </fieldset>
         <c:if test="${showDetails}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Penghantaran Borang B
                </legend>

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
                </p>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.listNotis}"
                                   cellpadding="0" cellspacing="0" requestURI="/lelong/kemasukkan_rekod_16H" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount">
                            <%--<c:if test="${line.pihak.pihak.nama ne null}">--%>
                                ${line.pihak.pihak.nama}<br>
                            <%--</c:if>--%>
                        </display:column>
                        <display:column title="Nama Penghantar Notis" >
                            <s:select name="namaPengahantar${line_rowNum-1}" id="namela${line_rowNum-1}" value="${line.penghantarNotis.idPenghantarNotis}" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                            &nbsp;

                        </display:column>

                        <display:column title="Status Penyampaian" class="${line_rowNum}">

                            <s:select name="kodPenyampaian${line_rowNum-1}" id="kodPenyampaian${line_rowNum-1}" value="${line.kodStatusTerima.kod}">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>

                        </display:column>

                        <display:column title="Cara Penghantaran" class="${line_rowNum}">

                            <s:select name="kodPenghantaran${line_rowNum-1}" id="kodPenghantaran${line_rowNum-1}" value="${line.kodCaraPenghantaran.kod}">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>

                        </display:column>

                        <display:column title="Tarikh" class="${line_rowNum}">
                            <p>H : <s:text class="datepicker" name="listNotis[${line_rowNum-1}].tarikhHantar" id="tarikhDihantar${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" value="${line.tarikhHantar}"/><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="listNotis[${line_rowNum-1}].tarikhTerima" id="tarikhTerima${line_rowNum-1}" formatPattern="dd/MM/yyyy" style="width:90px;" value="${line.tarikhTerima}"/><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            </display:column>
                            <display:column  title="Catatan" class="${line_rowNum}" >
                                <s:textarea name="listNotis[${line_rowNum-1}].catatanPenerimaan" id="catatanTerima${line_rowNum-1}" value="${line.catatanPenerimaan}" rows="3" cols="20" onblur="this.value=this.value.toUpperCase();" />
                            </display:column>
                            <display:column title="Tindakan">
                            <p align="center">
                                <c:if test="${line.buktiPenerimaan == null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                </c:if>
                                <c:if test="${line.buktiPenerimaan != null}">
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
                <c:if test="${actionBean.btn ne true}">
                    <p align="right">
                        <s:button class="btn"  name="simpan" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </div>
                         </c:if>
    </c:if>

             </div>
</s:form>
