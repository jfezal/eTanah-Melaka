<%--
    Document   : kemasukan_rekod
    Created on : Apr 21, 2010, 3:23:34 AM
    Author     : mdizzat.mashrom
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

    $(document).ready(function() {
        var bil = $(".rowCount").length;
        for (var i = 0; i < bil; i++){
            $('#viewReport'+i).hide();
        }
    });


    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/rekod_penghantaran?popup&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

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

    function muatNaikForm(idKehadiran, jenis, id) {

    <%--              var rowNo;
                  var namaPN;
                  var statusSampai;
                  var caraHantar;
                  var tarikhHantar;
                  var tarikhTerima;
                  var catatan;

                  if(jenis ==  'surat'){
                      rowNo = $('table#line2 tr').length;
                  }
                  else{
                      rowNo = $('table#line tr').length;
                  }

              for (var i = 0; i < rowNo-1; i++){

                  namaPN = $('#namaPN'+i).val();
                  statusSampai = $('#statusSampai'+i).val();
                  caraHantar = $('#caraHantar'+i).val();
                  tarikhHantar = $('#tarikhHantar'+i).val();
                  tarikhTerima = $('#tarikhTerima'+i).val();
                  catatan = $('#catatan'+i).val();
                  var url = '${pageContext.request.contextPath}/consent/maklumat_notis?simpanBicaraHadir&namaPN='+namaPN+'&statusSampai='+statusSampai+'&caraHantar='+caraHantar+'&tarikhHantar='+tarikhHantar+'&tarikhTerima='+tarikhTerima+'&catatan='+catatan+'&row='+i;
                  $.get(url,
                  function(data){
                      $('#page_div').html(data);
                  },'html');
              }--%>

                      var left = (screen.width/2)-(1000/2);
                      var top = (screen.height/2)-(150/2);
                      var url = '${pageContext.request.contextPath}/consent/maklumat_notis?popupUpload&idKehadiran='+idKehadiran+'&id='+id;
                      window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
                  }

                  function scan(idKehadiran) {
                      var url = '${pageContext.request.contextPath}/consent/maklumat_notis?popupScan&idKehadiran='+idKehadiran;
                      window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
                  }

                  function doViewReport(v) {
                      var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                      window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                  }

                  function doViewReport2(v) {
                      //alert("view 2");
                      var url = '${pageContext.request.contextPath}/consent/maklumat_notis?view&idHadir='+v;
                      window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
                  }
        
                  function addPenghantarNotis(){
                      window.open("${pageContext.request.contextPath}/consent/maklumat_notis?popupPenghantarNotis", "eTanah",
                      "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
                  }

                  function updateNotis(idKehadiran, id) {
                      var namaPN = $('#namaPN' + id).val();
                      var statusSampai = $('#statusSampai' + id).val();
                      var caraHantar = $('#caraHantar' + id).val();
                      var tarikhHantar = $('#tarikhHantar' + id).val();
                      var tarikhTerima = $('#tarikhTerima' + id).val();
                      var catatan = $('#catatan' + id).val();

    <%--alert(idKehadiran+"-"+namaPN+"-"+statusSampai+"-"+caraHantar+"-"+tarikhHantar+"-"+tarikhTerima+"-"+catatan);--%>

            var url = '${pageContext.request.contextPath}/consent/maklumat_notis?updateNotis&idKehadiran='+idKehadiran
                + '&namaPN=' + namaPN + '&statusSampai=' + statusSampai+ '&caraHantar=' + caraHantar+ '&tarikhHantar=' + tarikhHantar+ '&tarikhTerima=' + tarikhTerima+ '&catatan=' + catatan;
            $.post( url,
            function(data){
            }, 'html');
        }

    
</script>

<s:form beanclass="etanah.view.stripes.consent.MaklumatNotisActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle"  id="notis">
        <fieldset class="aras1">
            <p class=instr>
                <font color="red"><b>Petunjuk :</b></font>
            </p>
            <p>
                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                     width="24" height="24" alt="Papar" /> - <font size="2" color="black"><b>Papar Dokumen</b></font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                     width="24" height="24" alt="Muat Naik" /> - <font size="2" color="black"><b>Muat Naik Dokumen</b></font>&nbsp;&nbsp;&nbsp;
                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png"
                     width="24" height="24" alt="imbas"/> - <font size="2" color="black"><b>Imbas Dokumen</b></font>
            </p><br>
        </fieldset>
        <c:if test="${fn:length(actionBean.listSuratTangguh) > 0}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Penghantaran Surat Tangguh Bicara
                </legend><br>
                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.listSuratTangguh}" cellpadding="0" cellspacing="0" requestURI="/consent/maklumat_notis" id="line2">
                        <display:column title="Bil" sortable="true">${line2_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount">
                            <c:if test="${line2.pihak ne null}">
                                ${line2.pihak.pihak.nama}
                            </c:if>
                            <c:if test="${line2.pihak eq null}">
                                ${line2.nama}
                            </c:if>
                        </display:column>
                        <display:column title="Cara Penghantaran" class="${line2_rowNum}">
                            <s:select name="kodCaraPenghantaran${line2_rowNum-1}" id="caraHantar${line2_rowNum-1}" value="${line2.caraPenghantaran.kod}"
                                      onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"
                                      onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>

                        <display:column title="Nama Penghantar Notis" >
                            <s:select name="namaPengahantar${line2_rowNum-1}" id="namaPN${line2_rowNum-1}"
                                      value="${line2.penghantarNotis.idPenghantarNotis}" style="width:180px;"
                                      onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"
                                      onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.listPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>

                        <display:column title="Status Penyampaian" class="${line2_rowNum}">
                            <s:select name="kodStatusTerima${line2_rowNum-1}" id="statusSampai${line2_rowNum-1}" value="${line2.statusTerima.kod}"
                                      onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"
                                      onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>


                        <display:column title="Tarikh" class="${line2_rowNum}">
                            <p>Hantar : <s:text class="datepicker" id="tarikhHantar${line2_rowNum-1}" name="listSuratTangguh[${line2_rowNum-1}].tarikhHantar" formatPattern="dd/MM/yyyy" style="width:100px;"
                                    onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"
                                    onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                            <p>Terima : <s:text class="datepicker" id="tarikhTerima${line2_rowNum-1}" name="listSuratTangguh[${line2_rowNum-1}].tarikhTerima" formatPattern="dd/MM/yyyy" style="width:100px;"
                                    onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"
                                    onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                        </display:column>
                        <display:column  title="Catatan" class="${line2_rowNum}" >
                            <s:textarea name="listSuratTangguh[${line2_rowNum-1}].catatanTerima" id="catatan${line2_rowNum-1}" rows="4" cols="15"
                                        onblur="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}');this.value=this.value.toUpperCase();"
                                        onchange="updateNotis('${line2.idKehadiran}', '${line2_rowNum-1}')"/>
                        </display:column>

                        <display:column title="Tindakan">
                            <div align="center">
                                <c:if test="${line2.buktiTerima.namaFizikal == null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line2.idKehadiran}', 'surat', '${line2_rowNum-1}');return false;" title="Muat Naik Dokumen"/>
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line2.idKehadiran}');return false;" title="Imbas Dokumen"/>

                                    <div id="viewReport${line2_rowNum-1}">/<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                                                onclick="doViewReport2('${line2.idKehadiran}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/></div>

                                </c:if>
                                <c:if test="${line2.buktiTerima.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line2.idKehadiran}', 'surat');return false;" title="Muat Naik Dokumen"/>
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line2.idKehadiran}');return false;" title="Imbas Dokumen"/>
                                    / <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                           onclick="doViewReport('${line2.buktiTerima.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </c:if>
                            </div>
                        </display:column>
                    </display:table>
                </div>
                <div align="center">
                    <c:if test="${edit}">
                        <br/>
                        <p align="center">
                            <s:button class="btn" name="simpan" value="Simpan" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </p>
                    </c:if>
                </div>
                <br/>
            </fieldset>
            <br/>
        </c:if>
        <c:if test="${fn:length(actionBean.listSuratTangguhLama) > 0}">
            <fieldset class="aras1">
                <legend>
                    Maklumat Penghantaran Surat Tangguh Bicara Terdahulu
                </legend><br>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listSuratTangguhLama}" cellpadding="0" cellspacing="0" requestURI="/consent/maklumat_notis" id="line4">
                        <display:column title="Bil" sortable="true">${line4_rowNum}</display:column>
                        <display:column title="Nama">
                            <c:if test="${line4.pihak ne null}">
                                ${line4.pihak.pihak.nama}
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line4.nama}
                            </c:if>
                        </display:column>
                        <display:column title="Cara Penghantaran">
                            <c:if test="${line4.caraPenghantaran ne null}"><font style="text-transform:uppercase;"> ${line4.caraPenghantaran.nama} </font></c:if>
                            <c:if test="${line4.caraPenghantaran eq null}">TIADA DATA</c:if>
                        </display:column>
                        <display:column title="Nama Penghantar Notis" >
                            <c:if test="${line4.penghantarNotis ne null}"><font style="text-transform:uppercase;"> ${line4.penghantarNotis.nama}</font> </c:if>
                            <c:if test="${line4.penghantarNotis eq null}">TIADA DATA</c:if>
                        </display:column>
                        <display:column title="Status Penyampaian">
                            <c:if test="${line4.statusTerima ne null}"><font style="text-transform:uppercase;"> ${line4.statusTerima.nama} </font></c:if>
                            <c:if test="${line4.statusTerima eq null}">TIADA DATA</c:if>
                        </display:column>
                        <display:column title="Tarikh">
                            <p>Hantar :
                                <c:if test="${line4.tarikhHantar ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${line4.tarikhHantar}"/> </c:if>
                                <c:if test="${line4.tarikhHantar eq null}">TIADA DATA</c:if>
                            </p>
                            <p>Terima :
                                <c:if test="${line4.tarikhTerima ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${line4.tarikhTerima}"/> </c:if>
                                <c:if test="${line4.tarikhTerima eq null}">TIADA DATA</c:if>
                            </p>
                        </display:column>
                        <display:column  title="Catatan">
                            <c:if test="${line4.catatanTerima ne null}"> ${line4.catatanTerima} </c:if>
                            <c:if test="${line4.catatanTerima eq null}">TIADA DATA</c:if>
                        </display:column>

                        <display:column title="Paparan">
                            <p align="center">
                                <c:if test="${line4.buktiTerima.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${line4.buktiTerima.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </c:if>
                                <c:if test="${line4.buktiTerima.namaFizikal == null}">
                                    TIADA DATA
                                </c:if>
                            </p>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
            <br/>
        </c:if>
        <fieldset class="aras1">
            <legend>
                Maklumat Penghantaran Notis
            </legend><br>
            <c:if test="${fn:length(actionBean.listSuratTangguh) == 0}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/consent/maklumat_notis" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column title="Nama" class="rowCount">
                            <c:if test="${line.pihak ne null}">
                                ${line.pihak.pihak.nama}
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line.nama}
                            </c:if>
                        </display:column>
                        <display:column title="Cara Penghantaran" class="${line_rowNum}">
                            <s:select name="kodCaraPenghantaran${line_rowNum-1}" id="caraHantar${line_rowNum-1}" value="${line.caraPenghantaran.kod}"
                                      onblur="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"
                                      onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Nama Penghantar Notis" >
                            <s:select name="namaPengahantar${line_rowNum-1}" id="namaPN${line_rowNum-1}" value="${line.penghantarNotis.idPenghantarNotis}" style="width:180px;"
                                      onblur="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"
                                      onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.listPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" class="${line_rowNum}">
                            <s:select name="kodStatusTerima${line_rowNum-1}" id="statusSampai${line_rowNum-1}" value="${line.statusTerima.kod}"
                                      onblur="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"
                                      onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" class="${line_rowNum}">
                            <p>Hantar : <s:text class="datepicker" id="tarikhHantar${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhHantar" formatPattern="dd/MM/yyyy" style="width:100px;"
                                    onblur="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"
                                    onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                            <p>Terima : <s:text class="datepicker" id="tarikhTerima${line_rowNum-1}" name="listNotis[${line_rowNum-1}].tarikhTerima" formatPattern="dd/MM/yyyy" style="width:100px;"
                                    onblur="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"
                                    onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"/>
                                <br>
                                &nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
                            </p>
                        </display:column>
                        <display:column  title="Catatan" class="${line_rowNum}" >
                            <s:textarea name="listNotis[${line_rowNum-1}].catatanTerima" id="catatan${line_rowNum-1}" rows="4" cols="15"
                                        onblur="this.value=this.value.toUpperCase();updateNotis('${line.idKehadiran}', '${line_rowNum-1}');"
                                        onchange="updateNotis('${line.idKehadiran}', '${line_rowNum-1}')"/>
                        </display:column>

                        <display:column title="Tindakan">
                            <div align="center">
                                <c:if test="${line.buktiTerima.namaFizikal == null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idKehadiran}','notis','${line_rowNum-1}');return false;" title="Muat Naik Dokumen"/>
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idKehadiran}');return false;" title="Imbas Dokumen"/>
                                    <div id="viewReport${line_rowNum-1}">/<img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                                               onclick="doViewReport2('${line.idKehadiran}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/></div>
                                    </c:if>
                                    <c:if test="${line.buktiTerima.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                         onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idKehadiran}','notis');return false;" title="Muat Naik Dokumen"/>
                                    /
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                         onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idKehadiran}');return false;" title="Imbas Dokumen"/>
                                    / <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                           onclick="doViewReport('${line.buktiTerima.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </c:if>
                            </div>
                        </display:column>
                    </display:table>
                    <c:if test="${edit}">
                        <br/>
                        <p align="center">
                            <s:button class="btn" name="simpan" value="Simpan" onclick="doSubmit(this.form, this.name,'page_div')"/>
                        </p>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${fn:length(actionBean.listSuratTangguh) > 0}">
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listNotis}" cellpadding="0" cellspacing="0" requestURI="/consent/maklumat_notis" id="line3">
                        <display:column title="Bil" sortable="true">${line3_rowNum}</display:column>
                        <display:column title="Nama">
                            <c:if test="${line3.pihak ne null}">
                                ${line3.pihak.pihak.nama}
                            </c:if>
                            <c:if test="${line.pihak eq null}">
                                ${line3.nama}
                            </c:if>
                        </display:column>
                        <display:column title="Cara Penghantaran">
                            <c:if test="${line3.caraPenghantaran ne null}"> <font style="text-transform:uppercase;">${line3.caraPenghantaran.nama}</font> </c:if>
                            <c:if test="${line3.caraPenghantaran eq null}">TIADA DATA</c:if>
                        </display:column>

                        <display:column title="Nama Penghantar Notis" >
                            <c:if test="${line3.penghantarNotis ne null}"><font style="text-transform:uppercase;"> ${line3.penghantarNotis.nama} </font></c:if>
                            <c:if test="${line3.penghantarNotis eq null}">TIADA DATA</c:if>
                        </display:column>

                        <display:column title="Status Penyampaian">
                            <c:if test="${line3.statusTerima ne null}"><font style="text-transform:uppercase;"> ${line3.statusTerima.nama}</font> </c:if>
                            <c:if test="${line3.statusTerima eq null}">TIADA DATA</c:if>
                        </display:column>
                        <display:column title="Tarikh">
                            <p>Hantar :
                                <c:if test="${line3.tarikhHantar ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${line3.tarikhHantar}"/> </c:if>
                                <c:if test="${line3.tarikhHantar eq null}">TIADA DATA</c:if>
                            </p>
                            <p>Terima :
                                <c:if test="${line3.tarikhTerima ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${line3.tarikhTerima}"/> </c:if>
                                <c:if test="${line3.tarikhTerima eq null}">TIADA DATA</c:if>
                            </p>
                        </display:column>
                        <display:column  title="Catatan">
                            <c:if test="${line3.catatanTerima ne null}"> ${line3.catatanTerima} </c:if>
                            <c:if test="${line3.catatanTerima eq null}">TIADA DATA</c:if>
                        </display:column>

                        <display:column title="Paparan">
                            <p align="center">
                                <c:if test="${line3.buktiTerima.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                         onclick="doViewReport('${line3.buktiTerima.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                </c:if>
                                <c:if test="${line3.buktiTerima.namaFizikal == null}">
                                    TIADA DATA
                                </c:if>
                            </p>
                        </display:column>
                    </display:table>
                </div>
            </c:if>
            <br>
        </fieldset>
    </div>
</s:form>
