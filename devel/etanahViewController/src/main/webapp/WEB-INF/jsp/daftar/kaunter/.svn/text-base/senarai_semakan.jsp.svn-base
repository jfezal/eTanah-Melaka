<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <title>Belakang Kaunter | Senarai Semakan</title>

    </head>
    <body>

        <script language="javascript">
            $(document).ready(function(){
                $('#idPermohonan').blur(function(){
                    validatePermohonan();
                });

                $('form').submit(function(){
                    $.blockUI({
                        message: $('#displayBox'),
                        css: {
                            top:  ($(window).height() - 50) /2 + 'px',
                            left: ($(window).width() - 50) /2 + 'px',
                            width: '50px'
                        }
                    });
                });
            });

            function selectAll(a){
                for (i = 0; i < 100; i++){
                    var c = document.getElementById("kandunganFolder" + i);
                    if (c == null) break;
                    c.checked = a.checked;
                    if (c.checked && $('#bilSalinan' + i).val() == '') $('#bilSalinan' + i).val(1);
                    else if (!c.checked && $('#bilSalinan' + i).val() != '') $('#bilSalinan' + i).val('');
                }
            }

            function validatePermohonan(){
                var val = $("#idPermohonan").val();
                frm = this.form;
                if (val == null || val == "") return;
                $.get("${pageContext.request.contextPath}/daftar/check_idpermohonan?doCheckPermohonan&idPermohonan=" + val,
                function(data){
                    if(data == '1'){
                    } else if(data =='0'){
                        $("#idPermohonan").val("");
                        alert("ID Permohonan tidak wujud!");
                    }
                });
            }

            function toggleSelectDocument(selectInput, i){
                if(selectInput.checked){
                    if ($('#bilSalinan' + i).val() == ''){
                        $('#bilSalinan' + i).val(1);
                    }
                } else{
                    var c = document.getElementById("semua");
                    if (c.checked) c.checked = false;

                    if ($('#bilSalinan' + i).val() != null){
                        $('#bilSalinan' + i).val('');
                    }
                }
            }

        </script>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>

        <p class="title">Urusan : ${actionBean.namaUrusan}</p>
        <p class=title>Langkah 2: Senarai Semakan</p>

        <stripes:messages />
        <stripes:errors />

        <p class=instr>Pilih dokumen-dokumen yang diserahkan oleh penyerah. Pastikan dokumen-dokumen mandatori disertakan.</p>

        <fieldset class=aras1>

            <stripes:form action="/daftar/kaunter" >
                <br/>
                ID Fail : <stripes:text name="idFail" onkeyup="this.value=this.value.toUpperCase();"/> <em>atau</em> ID Permohonan <stripes:text name="strIdMohon" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();"/>
                <br/>
                <br/>
                <display:table name="${actionBean.senaraiDokumen}" id="row"  class="tablecloth"  >
                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                        <stripes:checkbox name="senaraiDokumenSerahan[${row_rowNum - 1}].kodDokumen"
                                          value="${row.kodDokumen.kod}" id="kandunganFolder${row_rowNum - 1}" 
                                          onclick="javascript:toggleSelectDocument(this, ${row_rowNum - 1});"/>
                    </display:column>
                    <display:column title="Dokumen" >
                        ${row.kodDokumen.kod} - ${row.kodDokumen.nama}
                    </display:column>
                    <display:column title="Bil." >
                        <stripes:text size="3" name="senaraiDokumenSerahan[${row_rowNum - 1}].bil" id="bilSalinan${row_rowNum - 1}"/>
                    </display:column>
                    <display:column title="Mandatori?" >
                        <div align=center><img src="${pageContext.request.contextPath}/pub/images/${fn:contains(row.wajib,'Y') ? 'ok.png' : 'not_ok.gif'}"></div>
                        </display:column>
                        <display:column title="Perlu Disahkan" >
                        <div align=center><img src="${pageContext.request.contextPath}/pub/images/${fn:contains(row.perluDisah, 'Y') ? 'ok.png' : 'not_ok.gif'}"></div>
                        </display:column>
                        <display:column title="Arahan Tamb." >
                            ${row.catatan}
                        </display:column>
                        <display:column title="No.Rujukan" >
                            <stripes:text name="urusan.senaraiDokumenSerahan[${row_rowNum - 1}].noRujukan" size="15" />
                        </display:column>
                        <display:column title="Catatan" >
                            <stripes:text name="urusan.senaraiDokumenSerahan[${row_rowNum - 1}].catatan" size="20" />
                        </display:column>
                    </display:table>

                <c:set scope="request" var="kodDokumenNotRequired" value="${actionBean.kodDokumenNotRequired}" />

                Dokumen-dokumen tambahan yang diserahkan (pilih Kod Dokumen atau masukkan Catatan):
                <display:table name="${actionBean.senaraiDokumenTamb}" id="row1"  class="tablecloth"  >
                    <display:column title="Pilih" >
                        <stripes:select name="senaraiDokumenTamb[${row1_rowNum - 1}].kodDokumen"
                                        style="width:400px;">
                            <stripes:option value="0">Pilih ...</stripes:option>
                            <stripes:options-collection collection="${kodDokumenNotRequired}"
                                                        label="nama" value="kod" />
                        </stripes:select>
                    </display:column>
                    <display:column title="Catatan" >
                        <stripes:text name="senaraiDokumenTamb[${row1_rowNum - 1}].catatan" size="50" />
                    </display:column>
                </display:table>


                <p><label>&nbsp;</label>
                    <stripes:submit name="Step1" value="Kembali" class="btn" />
                    <%--${actionBean.urusan}--%>
                    <c:if test="${actionBean.urusan ne 'HSBM' &&  actionBean.urusan 
                                  ne 'HKBM' && actionBean.urusan ne 'HT' && actionBean.urusan 
                                  ne 'HTSC' && actionBean.urusan ne 'HTSPB' && actionBean.urusan 
                                  ne 'HTSPV' && actionBean.urusan ne 'HTSPS' && actionBean.urusan ne 'BMSTM'}">
                        <stripes:submit name="Step3" value="Seterusnya" class="btn" />
                    </c:if>
                    <c:if test="${actionBean.urusan eq 'HSBM' 
                                  || actionBean.urusan eq 'BMSTM'
                                  || actionBean.urusan eq 'HKBM' && actionBean.urusan ne 'HT' && actionBean.urusan ne 'HTSC' && actionBean.urusan ne 'HTSPB' && actionBean.urusan ne 'HTSPV' && actionBean.urusan ne 'HTSPS'}">
                        <stripes:submit name="Step3b" value="Seterusnya" class="btn" />
                    </c:if>
                    <c:if test="${actionBean.urusan eq 'HT' && actionBean.urusan ne 'HSBM' && actionBean.urusan ne 'HKBM' || actionBean.urusan eq 'HTSC' || actionBean.urusan eq 'HTSPB' || actionBean.urusan eq 'HTSPV' || actionBean.urusan eq 'HTSPS'}">
                        <stripes:submit name="Step5" value="Seterusnya" class="btn" />
                    </c:if>
                    <%-- <stripes:submit name="Step3" value="Seterusnya" class="btn" />--%>
                    <%--<c:if test="${actionBean.urusan ne 'MH'}">
                        <stripes:submit name="Step3" value="Seterusnya" class="btn" />
                    </c:if>
                    <c:if test="${actionBean.urusan eq 'MH'}">
                        <stripes:submit name="Step4" value="Seterusnya" class="btn" />
                    </c:if>--%>
                </p>

            </fieldset>
        </stripes:form>

    </body>
</html>