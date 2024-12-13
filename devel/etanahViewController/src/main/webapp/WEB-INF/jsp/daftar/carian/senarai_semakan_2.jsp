<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/pub/styles/styles.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
        <title>Carian | Senarai Semakan</title>

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

            function toggleSelectDocument(selectInput, i, v){
                if(selectInput.checked){
                    if ($('#bilSalinan' + i).val() == ''){
                        $('#bilSalinan' + i).val(1);
                    }
                    $('#idDokumen' + i).val(v);
                } else{
                    var c = document.getElementById("semua");
                    if (c.checked) c.checked = false;

                    if ($('#bilSalinan' + i).val() != null){
                        $('#bilSalinan' + i).val('');
                    }
                    $('#idDokumen' + i).val('');
                }
            }

        </script>
        <img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none"/>        
        <p class=title>
            Langkah 3: Senarai Semakan
        </p>

        <stripes:messages />
        <stripes:errors />

        <p class=instr>Sila pilih dokumen yang dikehendaki.</p>

        <fieldset class=aras1>

            <stripes:form action="/daftar/carian" >

                <display:table name="${actionBean.senaraiDokumenSSLSC}" id="row"  class="tablecloth"  >                    
                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' class='readonly'/>">
                        <stripes:checkbox name="senaraiDokumenSerahan[${row_rowNum - 1}].kodDokumen"
                                          value="${row.dokumen.kodDokumen.kod}" id="kandunganFolder${row_rowNum - 1}" 
                                          onclick="javascript:toggleSelectDocument(this, ${row_rowNum - 1}, ${row.dokumen.idDokumen});" class="readonly"/>
                    </display:column>
                    <display:column title="Dokumen" >
                        ${row.dokumen.kodDokumen.kod} - ${row.dokumen.kodDokumen.nama}
                        <stripes:hidden name="senaraiDokumenSerahan[${row_rowNum - 1}].noRujukan" id="idDokumen${row_rowNum - 1}"/>
                    </display:column>
                         <display:column title="Tajuk" >
                        ${row.dokumen.tajuk} - ${row.dokumen.tajuk}
                    </display:column>
                     <%--
                    <display:column title="Bil." >
                        <stripes:text size="3" name="senaraiDokumenSerahan[${row_rowNum - 1}].bil" id="bilSalinan${row_rowNum - 1}" class="readonly"/>
                    </display:column>
                     --%>                     
                </display:table>

                <p><label>&nbsp;</label>
                    <stripes:hidden name="nextStep" value=""/>
                    <stripes:submit name="Step2" value="Kembali" class="btn" />
                    <stripes:submit name="Step2c" value="Seterusnya" class="btn" />
                </p>

            </fieldset>
        </stripes:form>

    </body>
</html>