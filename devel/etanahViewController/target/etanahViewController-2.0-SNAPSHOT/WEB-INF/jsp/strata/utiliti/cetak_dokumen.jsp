<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
<script type="text/javascript">
    function addNew(v){
        window.open("${pageContext.request.contextPath}/strata/utiliti/dokumenTertinggal?addDocForm&permohonan.idPermohonan=" + v, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function signForm(v){
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?signForm';
        window.open(url, 'etanah', "status=1,toolbar=0,location=0,menubar=0,width=1000,height=600");
    }

    function doViewReport(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function refreshPage(){
        window.location.reload();
    }

    function doViewLog(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?viewLogDocument';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }

    function doEditDoc(v) {
        var url = '${pageContext.request.contextPath}/dokumen/view/' + v + '?showEditForm';
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=400,scrollbars=yes");
    }

    function doPrintViaApplet (docId) {
        //alert('tsttt');
        var a = document.getElementById('applet');        
        a.doPrint(docId.toString());
        //a.printDocument(docId.toString());
    }

    function muatNaikForm(folderId, dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/upload?muatNaikForm&folderId=' + folderId + '&dokumenId='
            + dokumenId + '&idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
    }

    function scan(dokumenId, idPermohonan) {
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
    }

    function saveFolderInfoEvent(event, frm) {
        var q = $(frm).formSerialize();
        var url = frm.action + '?' + event;
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : q,
            success : function(data) {
                $('#folder_div').html(data);
            }
        });
    }

    function selectAll(a){
        var len = $('.cetakSemua2').length;        
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);
            c.checked = a.checked;
        }	
    }

    function printSelectedDocuments () {
        var documentsList = '';
        var len = $('.cetakSemua2').length;    
        //alert("length : "+len);
        for (var i=0; i<len; i++) {
            var c = document.getElementById("cetaksemua2" + i);            
            if (c.checked) {                
                documentsList = documentsList +',' + c.value;
                //     alert("document List : "+documentsList);
            }
        }        
        if (documentsList == '') {
            alert('Sila Pilih Dokumen untuk dicetak.');
            return;
        } else {
            doPrintViaApplet(documentsList) ;
        }
    }
    
    function deleteItem(event, frm) {
        var q = $(frm).formSerialize();
        var url = frm.action + '?' + event;
        $.ajax({
            type:"POST",
            url : url,
            dataType : 'html',
            data : q,
            success : function(data) {
                $('#folder_div').html(data);
            }
        });
    }

    function doViewImage (val) {
        var url = '${pageContext.request.contextPath}/dokumen/view/image/' + val;
        window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600,scrollbars=yes");
    }

    function saveInput(val, t) {        
        var url = '${pageContext.request.contextPath}/strata/utiliti/dokumenTertinggal?saveEditInfo&tajuk=' + val + '&idDok=' + $('#val_' + t.id).val();        
        $.post(url,
        function(data){
            if (data == '1') $('#' + t.id).text(val);
            else $('#' + t.id).text($('#old_' + t.id).val());
        });        
    }
            
    $(document).ready(function(){
        
        $('#p').click( function(){
            $('#folder').click();
        });
        $('.editable').each(function(i) {            
            $(this).dblclick(function() {
                $(this).html('<input id="'+i+'" type=text onblur=\'saveInput(this.value, this);\'/>');
            });
        });        

        $('#f1').click(function(){
            if($('#f1').hasClass('open')){
                $('#row1').slideUp('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f1').removeClass('open');
                $('#f1').addClass('close');
            }else if($('#f1').hasClass('close')){
                $('#row1').slideDown('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f1').removeClass('close');
                $('#f1').addClass('open');
            }
        });
        $('#f11').click(function(){
            if($('#f11').hasClass('open')){
                $('#row1').slideUp('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f11').removeClass('open');
                $('#f11').addClass('close');
            }else if($('#f11').hasClass('close')){
                $('#row1').slideDown('normal');
                $('#f11').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f11').removeClass('close');
                $('#f11').addClass('open');
            }
        });

        $('#f2').click(function(){
            if($('#f2').hasClass('open')){
                $('#row2').slideUp('normal');
                $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folder.gif');
                $('#f2').removeClass('open');
                $('#f2').addClass('close');
            }else if($('#f2').hasClass('close')){
                $('#row2').slideDown('normal');
                $('#f2').attr('src', '${pageContext.request.contextPath}/pub/images/folderopen.gif');
                $('#f2').removeClass('close');
                $('#f2').addClass('open');
            }
        });

        //$('#row2').hide();
    });
    
    function doSearch(f,e){       
        var v = $('#idPermohonan').val();
        if(v == ''){
            alert('Sila masukkan ID Permohonan.');
            $('#idPermohonan').focus();
            return;
        }
        f.action = f.action + '?' + e;        
        f.submit();
    }
    
    function clearForm(f) {
        $('#idPermohonan').val('');
    }
</script>
<div class="subtitile"> 
    <s:errors/>
    <s:messages/>
    <div class="displaytag">
        <s:form beanclass="etanah.view.strata.utiliti.UtilitiCetakActionBean" id="tambah">
            <fieldset class="aras1">
                <s:hidden name="folderDokumen.folderId"/> 
                <legend>Cetak Semula Dokumen</legend>
                <p>
                    <label>ID Permohonan :</label>
                    <s:text name="permohonan.idPermohonan" size="34" maxlength="20" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                </p>        
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="search" value="Cari" class="btn" onclick=""/>&nbsp;
                    <s:submit name="reset" value="Isi Semula" class="btn" onclick=""/>
                </p>
                <br/>
            </fieldset>

            <c:if test="${actionBean.flag}">
                <fieldset class="aras1">
                    <legend>Senarai Dokumen</legend>
                    <p>
                        <font color="green" size="-1"> Arahan : Sila pilih dokumen dan klik butang 'Cetak' </font><br/><br/>
                        <label>ID Permohonan :</label>
                        ${actionBean.permohonan.idPermohonan}                
                    </p>
                   
                    <p>
                        <label>Kod Urusan/Urusan :</label>
                        ${actionBean.permohonan.kodUrusan.kod} - 
                        ${actionBean.permohonan.kodUrusan.nama}
                    </p>
                    <br/>

                    <c:set var="rowNum" value="0"/>
                    <c:set var="rowNum2" value="0"/>
                    <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row" style="width:100%" requestURI="${pageContext.request.contextPath}/dokumen/folder">
                        <display:column title="Pilih<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                            <c:if test="${row.dokumen.namaFizikal != null && (actionBean.pengguna.tahapSekuriti.kod >= row.dokumen.klasifikasi.kod)}">                    
                                <p align="center">
                                    <input type="checkbox" name="cetaksemua2" id="cetaksemua2${rowNum2}" value="${row.dokumen.idDokumen}" class="cetakSemua2"/>
                                </p>
                                <c:set var="rowNum2" value="${rowNum2 +1}"/>
                            </c:if>
                        </display:column>        
                        <display:column title="Bil">${row_rowNum}</display:column>
                        <display:column title="Kod Dokumen" group="1">
                            <div id="t" title="${row.dokumen.kodDokumen.nama}">${row.dokumen.kodDokumen.kod}</div>
                        </display:column>
                        <display:column title="Tajuk /<br/> No Rujukan">
                            <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                <div id="${row_rowNum-1}">${row.catatan}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.catatan}"/>
                            </c:if>
                            <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                <div id="${row_rowNum-1}">${row.dokumen.tajuk}</div>
                                <s:hidden name="x" id="old_${row_rowNum-1}" value="${row.dokumen.tajuk}"/>
                            </c:if>
                            <c:if test="${row.dokumen.kodDokumen.kod eq 'SB' 
                                          || row.dokumen.kodDokumen.kod eq 'SA'  
                                          || row.dokumen.kodDokumen.kod eq 'SW' }">
                                  <br/>  
                                  <c:if test="${row.dokumen.noRujukan ne '' && row.dokumen.noRujukan ne null}">No rujukan : 
                                      <a href="#" onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly&idMohon=${row.dokumen.noRujukan}',
                                          'popup','width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0'); ">
                                          ${row.dokumen.noRujukan})</a>
                                      </c:if>
                                  </c:if>
                            </display:column>
                            <display:column title="Klas." property="dokumen.klasifikasi.nama" />
                            <display:column title="Dimasuk Oleh" property="dokumen.infoAudit.dimasukOleh.nama" />
                            <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" sortable="true"/>
                            <display:column title="Tindakan">
                            <p align="center">
                                <c:if test="${row.dokumen.namaFizikal != null}">
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${row.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                    <c:if test="${row.dokumen.baru eq 'Y' || row.dokumen.baru eq ''}">
                                        <img src="${pageContext.request.contextPath}/pub/images/baharu.gif" alt="baru"/>
                                    </c:if>
                                </c:if>
                            </p>
                        </display:column>
                        <display:column title="Cetak">
                            <c:if test="${row.dokumen.namaFizikal != null && (actionBean.pengguna.tahapSekuriti.kod >= row.dokumen.klasifikasi.kod)}">                    
                                <p align="center">
                                <input:hidden type="checkbox" name="cetaksemua" id="cetaksemua${rowNum}" value="${row.dokumen.idDokumen}" class="cetakSemua"/>
                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                     onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" height="30" width="30" alt="cetak"
                                     onmouseover="this.style.cursor='pointer';" title="Cetak Dokumen ${row.dokumen.kodDokumen.nama}"/>
                                </p>
                                <c:set var="rowNum" value="${rowNum +1}"/>
                            </c:if>
                        </display:column>        
                    </display:table><br>      
                    <p align="center">
                        <s:button name="cetakSemua2" value="Cetak" class="btn" onclick="printSelectedDocuments();"/>
                    </p>
                    <br/>
                </fieldset><br/>

                <applet code="PrintApplet.class" ARCHIVE="${pageContext.request.contextPath}/PrintApplet.jar,
                        ${pageContext.request.contextPath}/commons-httpclient-2.0.2.jar,
                        ${pageContext.request.contextPath}/commons-logging.jar,
                        ${pageContext.request.contextPath}/swingx-1.6.jar,
                        ${pageContext.request.contextPath}/log4j-1.2.12.jar,
                        ${pageContext.request.contextPath}/jpedal_demo.jar"
                        codebase = "."
                        name     = "applet"
                        id       = "applet"
                        width    = "1"
                        height   = "1"
                        align    = "middle">
                    <param name="uploadAction" value="<%=request.getContextPath()%>/PrintServlet"/>
                    <param name ="kodNegeri" value="05">
                    <param name ="method" value="pdfp">
                    <!--      <param name ="disabledWatermark" value="true"/>--> 
                    <param name ="idPengguna" value="${idPengguna}"/>
                    <%
                        Cookie[] cookies = request.getCookies();
                        StringBuffer sb = new StringBuffer();
                        for (int i = 0; i < cookies.length; i++) {
                            sb.setLength(0);
                            sb.append(cookies[i].getName());
                            sb.append("=");
                            sb.append(cookies[i].getValue());
                    %>
                    <param name="Cookie<%= i%>" value="<%= sb.toString()%>"><%
                        }
                    %>
                </applet>

                <c:if test="${fn:length(actionBean.folderDokumenSebelum.senaraiKandungan) > 0
                              && actionBean.folderDokumenSebelum.folderId ne actionBean.folderDokumen.folderId}">
                      <p class=title><img src="${pageContext.request.contextPath}/images/folderopen.gif" id="f2" class="open"/>
                          Fail ${actionBean.folderDokumenSebelum.tajuk}</p>
                      <p class=instr>Senarai Dokumen dalam Fail untuk permohonan terdahulu.</p>
                      <br/>
                      <fieldset class="aras1" id="row2">
                          <legend>Maklumat Fail Permohonan Sebelum</legend>
                          <p>
                              <label>Carian :</label>
                              <s:select name="filterBy2" style="width:400" onchange="doSubmit(this.form, 'reload', 'folder_div');">
                                  <s:option value="">Semua dokumen</s:option>
                                  <s:options-map map="${actionBean.kodMapSebelum}"/>
                              </s:select>
                          </p>
                          <br/>
                          <display:table name="${actionBean.senaraiKandunganSebelum}" class="tablecloth" id="row2" style="width:100%">
                              <display:column title="Bil">${row2_rowNum}</display:column>
                              <display:column title="Kod Dokumen" property="kodDokumen.kod" />
                              <display:column title="Tajuk / <br/> No Rujukan">
                                  <c:if test="${row2.kodDokumen.kod eq '0L'}">
                                      <div id="${row2_rowNum-1}">${row2.catatan}</div>
                                      <s:hidden name="x2" id="old2_${row2_rowNum-1}" value="${row2.catatan}"/>
                                  </c:if>
                                  <c:if test="${row2.kodDokumen.kod ne '0L'}">
                                      <div id="${row2_rowNum-1}">${row2.tajuk}</div>
                                      <s:hidden name="x2" id="old2_${row2_rowNum-1}" value="${row2.tajuk}"/>
                                  </c:if>
                                  <c:if test="${row2.kodDokumen.kod eq 'SB' 
                                                || row2.kodDokumen.kod eq 'SA'  
                                                || row2.kodDokumen.kod eq 'SW' }">
                                        <br/>  
                                        <c:if test="${row2.noRujukan ne '' && row2.noRujukan ne null}">
                                            (No rujukan : <a href="#"
                                                             onclick="window.open('${pageContext.request.contextPath}/daftar/pertanyaanSBSWSA?paparOnly&idMohon=${row2.noRujukan}',
                                                                 'popup','width=1000,height=600,scrollbars=yes,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0'); ">
                                                ${row2.noRujukan})</a>
                                            </c:if>
                                        </c:if>
                                  </display:column>
                                  <display:column title="Klasifikasi" property="klasifikasi.nama" />
                                  <display:column title="Dimasuk Oleh" property="infoAudit.dimasukOleh.nama" />
                                  <%--<display:column title="Catatan" property="catatan" />--%>
                                  <display:column title="Tarikh" property="infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                                  <display:column title="Tindakan">
                                  <p align="center">
                                      <c:if test="${row2.namaFizikal != null}">
                                          <%--<a href="#" onclick="doViewReport('${row.dokumen.idDokumen}');return false;">Papar</a>--%>

                                          <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                               onclick="doViewReport('${row2.idDokumen}');" height="30" width="30" alt="papar"
                                               onmouseover="this.style.cursor='pointer';" title="Papar ${row2.kodDokumen.nama}"/>                                  
                                      </c:if>
                                  </p>
                              </display:column>
                              <display:column title="Cetak">
                                  <c:if test="${row2.namaFizikal != null}">
                                      <%--<s:button name="cetak" value="Cetak" onclick="doPrintViaApplet('${row.dokumen.idDokumen}');" class="btn"/>--%>
                                      <p align="center">
                                          <img src="${pageContext.request.contextPath}/pub/images/icons/_active__print.png"
                                               onclick="doPrintViaApplet('${row2.idDokumen}');" height="30" width="30" alt="cetak"
                                               onmouseover="this.style.cursor='pointer';" title="Cetak ${row2.kodDokumen.nama}"/>
                                      </p>
                                  </c:if>
                              </display:column>
                          </display:table>
                      </c:if>
                      <br/>
                </fieldset>
            </c:if>
        </s:form>
    </div>
</div>
