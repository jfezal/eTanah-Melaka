<%--
    Document   : penyampaianNotisJPPHUPEN
    Created on : 27-Jun-2011, 15:38:54
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


            var nama = document.getElementById('idPenghantarNotis');
            var kod = document.getElementById('kodStatusTerima');
            var kod2 = document.getElementById('kodCaraPenghantaran');
            var tarikh = document.getElementById('tarikhHantar');
            var tarikh2 = document.getElementById('tarikhTerima');
            var catat = document.getElementById('catatanPenerimaan');
            if(nama.value == "" ){
                alert("Sila Masukkan Nama Penghantar Notis");
                $('#idPenghantarNotis').focus();
                return false;
            }
            if(kod.value == "" ){
                alert("Sila Pilih Status Penyampaian");
                $('#kodStatusTerima').focus();
                return false;
            }
            if(kod2.value == "" ){
                alert("Sila Pilih Cara Penghantaran");
                $('#kodCaraPenghantaran').focus();
                return false;
            }
            if(tarikh.value == "" ){
                alert("Sila Pilih Tarikh Hantar");
                $('#tarikhHantar').focus();
                return false;
            }
            if(tarikh2.value == "" ){
                alert("Sila Pilih Tarikh Terima");
                $('#tarikhTerima').focus();
                return false;
            }
            if(catat.value == "" ){
                alert("Sila Masukkan Catatan");
                $('#catatanPenerimaan').focus();
                return false;
            }
        return true;
    }


    function muatNaikForm(idNotis) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        <%--var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?popupUpload&idPihak='+idPihak+'&idHakmilik='+idHakmilik+'&showPP='+showPP+'&showHP='+showHP;--%>
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?popupUpload&idNotis='+idNotis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function muatNaikFormPemohon(idPihak) {
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?popupUpload&idPihak='+idPihak+'&idHakmilik='+idHakmilik+'&showPP='+showPP+'&showHP='+showHP+'&isPemohonPihak=true';
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
    }

    function addPenghantarNotis(){
        window.open("${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?popupPenghantarNotis", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
    }

    function scan(notis) {
        var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?popupScan&idNotis='+notis;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }

    function refreshNotis(idHakmilik,showPP,showHP,isMahkamah){
            var url = '${pageContext.request.contextPath}/pengambilan/penerimaan_notis_jpphupen?showForm3';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

    }


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.PenyampaianNotisJPPHUPENActionBean" id="folder_div">

    <s:messages/>
    <s:errors/>&nbsp;
    <%--<c:if test="${actionBean.show ne true}">--%>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Penghantaran Surat/Borang/Notis
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
                <c:if test="${actionBean.show eq true && actionBean.notisPermohonan ne null }">

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.notisPermohonan}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_jpphupen" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <%--<display:column title="Nama" class="rowCount">
                            <c:if test="${line.kodNotis.kod eq 'JPP'}">
                                JPPH<br>
                            </c:if>
                            <c:if test="${line.kodNotis.kod eq 'UPE'}">
                                UPEN<br>
                            </c:if>
                        </display:column>--%>
                        <display:column title="Nama Penghantar Notis" >
                            <s:select name="idPenghantarNotis" id="idPenghantarNotis" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                            &nbsp;

                        </display:column>

                        <display:column title="Status Penyampaian" class="${line_rowNum}">

                            <s:select name="kodStatusTerima" id="kodStatusTerima" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>

                        </display:column>

                        <display:column title="Cara Penghantaran" class="${line_rowNum}">

                            <s:select name="kodCaraPenghantaran" id="kodCaraPenghantaran" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>

                        </display:column>

                        <display:column title="Tarikh" class="${line_rowNum}">
                            <p>H : <s:text class="datepicker" name="tarikhHantar" id="tarikhHantar" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerima" id="tarikhTerima" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            </display:column>
                            <display:column  title="Catatan" class="${line_rowNum}" >
                                <s:textarea name="catatanPenerimaan" id="catatanPenerimaan" rows="3" cols="20" onblur="this.value=this.value.toUpperCase();" />
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
                            <%--<p align="center">
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                    </c:if>
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] ne ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${actionBean.idDokumenList[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>
                                </p>--%>
                        </display:column>
                    </display:table>

                </div>
                    </c:if>
                 <c:if test="${actionBean.showPP eq true && actionBean.notisPemohon ne null}">
                <div align="center" id="pemohonList">
                    <display:table class="tablecloth" name="${actionBean.notisPemohon}"
                                   cellpadding="0" cellspacing="0" requestURI="/pengambilan/penerimaan_notis_jpphupen" id="line">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                        <display:column property="pihak.pihak.nama" title="Nama"/>
                        <display:column title="Nama Penghantar Notis " >
                            <s:select name="idPenghantarNotis" id="idPenghantarNotis" style="width:150px;">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiPenghantarNotis}" label="nama" value="idPenghantarNotis"/>
                            </s:select>
                        </display:column>
                        <display:column title="Status Penyampaian" >
                            <s:select name="kodStatusTerima" id="kodStatusTerima" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodStatusTerima}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Cara Penghantaran" >
                            <s:select name="kodCaraPenghantaran" id="kodCaraPenghantaran" >
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodCaraPenghantaran}" label="nama" value="kod"/>
                            </s:select>
                        </display:column>
                        <display:column title="Tarikh" >
                            <p>H : <s:text class="datepicker" name="tarikhHantar" id="tarikhHantar" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                            <p>T : <s:text class="datepicker" name="tarikhTerima" id="tarikhTerima" formatPattern="dd/MM/yyyy" style="width:90px;" /><br><font size="1" color="red"> (cth : hh / bb / tttt)</font></p>
                        </display:column>
                        <display:column  title="Catatan" >
                            <s:textarea name="catatanPenerimaan" id="catatanPenerimaan" rows="3" cols="20" />
                        </display:column>
                        <display:column title="Tindakan">
                        <%--<p align="center">
                            <c:if test="${line.buktiPenerimaan == null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormPemohon('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanPemohon('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                            </c:if>
                            <c:if test="${line.buktiPenerimaan != null}">
                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikFormPemohon('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                     onmouseover="this.style.cursor='pointer';" onclick="scanPemohon('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                /
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                     onclick="doViewReport('${line.buktiPenerimaan.idDokumen}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                            </c:if>
                                <p align="center">
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                    </c:if>
                                    <c:if test="${actionBean.idDokumenList[line_rowNum-1] ne ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                             onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${line.idNotis}');return false;" title="Muat Naik Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                             onmouseover="this.style.cursor='pointer';" onclick="scan('${line.idNotis}');return false;" title="Imbas Dokumen"/>
                                        /
                                        <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                             onclick="doViewReport('${actionBean.idDokumenList[line_rowNum-1]}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                    </c:if>
                                </p>
                        </p>--%>
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
                    </display:table><br /><br />
                </div>
            </c:if>
                <c:if test="${actionBean.btn ne true}">
                    <p align="right">
                        <s:button class="btn"  name="simpanNew" value="Simpan" onclick="if(validate())doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
                <br>
            </fieldset>
        </div>
    <%--</c:if>--%>
</s:form>

