<%--
    Document   :  tambah_dokumen
    Created on :  Jul 19, 2010, 4:44:13 PM
    Author     :  Siti Fariza Hanim @ some fix by @mr5rule
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tambah Dokumen</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
        <script type="text/javascript">


            function removeDok(val,val2){
                if(confirm('Adakah anda pasti?')) {
                    var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?deleteDok&idDokumen='+val+'&idRujukan='+val2;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                    },'html');
                }
            }


            function addDokumen(val){
                var len = $('.dok').length;
                var param = '';
                for(var i=0; i<=len; i++){
                    var ckd = $('#chkbox_pemohon_'+i).is(":checked");
                    if(ckd){
                        param = param + '&idDokumen=' + $('#chkbox_pemohon_'+i).val();
                    }
                }
                if(param == ''){
                    alert('Tiada Dokumen.');
                    return;
                }
                var url = '${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?simpanDokumen'+param +'&idRujukan='+val;

                $.ajax({
                    type:"GET",
                    url : url,
                    dataType : 'html',
                    error : function (xhr, ajaxOptions, thrownError){
                        alert("error=" + xhr.responseText);
                    },
                    success : function(data){
                        $('#page_div').html(data);
                    }
                });
            }

           function reload(v)
    {
        var frm = document.form1;
        var url = "${pageContext.request.contextPath}/pelupusan/jabatan_teknikal12?addDokumen&idRujukan="+v;
        frm.action = url;
        frm.submit();

    }


        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.pelupusan.JabatanTeknikal2ActionBean" name="form1">
                 <s:hidden name="idRujukan" id="idRujukan" />
                <div class="content" align="center">
                    <fieldset class="aras1">
                        <legend>
                            Senarai Dokumen Yang Perlu DiHantar
                        </legend>
                        <br>
                        Sila Klik pada kotak dan tekan Pilih untuk memilih Dokumen
                        <display:table name="${actionBean.senaraiKandungan}" class="tablecloth" id="row">
                            <display:column title="Pilih">
                                <s:checkbox name="checkbox" id="chkbox_pemohon_${row_rowNum}" value="${row.dokumen.idDokumen}"/>
                            </display:column>
                            <display:column title="Kod Dokumen" class="dok">
                                ${row.dokumen.kodDokumen.kod}
                            </display:column>
                            <display:column title="Tajuk / No Rujukan"  >
                                <c:if test="${row.dokumen.kodDokumen.kod eq '0L'}">
                                    <div id="${row_rowNum-1}" class="editable">${row.catatan}</div>
                                    <s:hidden name="x" id="old_${row_rowNum}" value="${row.catatan}"/>
                                </c:if>
                                <c:if test="${row.dokumen.kodDokumen.kod ne '0L'}">
                                    <div id="${row_rowNum-1}" class="editable">${row.dokumen.tajuk}</div>
                                    <s:hidden name="x" id="old_${row_rowNum}" value="${row.dokumen.tajuk}"/>
                                </c:if>
                                <br/>
                            </display:column>
                            <display:column title="Tarikh" property="dokumen.infoAudit.tarikhMasuk" format="{0,date,dd/MM/yyyy hh:mm:ss}" />
                        </display:table>

                        <br>
                        <p>
                            <s:button class="btn" value="Pilih" name="pilih" id="pilih" onclick="addDokumen('${actionBean.idRujukan}');" onmouseover="this.style.cursor='pointer';"/>&nbsp;
                               <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
                    </p>
                        </p>
                    </fieldset>
                </div>

                <div class="content" align="center">
                    <fieldset class="aras1">
                        <legend>
                            Senarai Dokumen Yang Telah DiPilih
                        </legend>
                        <br>
                        <display:table class="tablecloth" name="${actionBean.senaraiRujukanDok}" cellpadding="0" cellspacing="0" id="line">
                            <display:column title="Bil" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.dokumen.idDokumen}"/>
                            </display:column>
                            <display:column title="Kod Dokumen">
                                ${line.dokumen.kodDokumen.kod}
                            </display:column>
                            <display:column title="Tajuk / No Rujukan">
                                ${line.dokumen.kodDokumen.nama}
                            </display:column>
                            <display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onclick="removeDok('${line.dokumen.idDokumen}','${actionBean.idRujukan}')" onmouseover="this.style.cursor='pointer';">
                                </div>
                            </display:column>
                        </display:table>
                        <br>
                        <p>
                                  <s:button name="test" value="Klik Untuk Refresh" class="longbtn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.idRujukan}')"/>
                            </p>
                    </fieldset>
                </div>

            </s:form>
        </div>
    </body>
</html>
