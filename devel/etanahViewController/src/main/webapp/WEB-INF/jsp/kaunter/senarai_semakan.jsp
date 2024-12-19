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
        <title>Kaunter: Senarai Semakan</title>

    </head>
    <body>

        <script language="javascript">
            $(document).ready(function(){
                $('input:text').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
                });
                $('select').each(function(){
                    $(this).focus(function() { $(this).addClass('focus')});
                    $(this).blur(function() { $(this).removeClass('focus')});
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

            // change the Bil column of the checklist
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

        <p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
        <p class=title>Langkah 2: Senarai Semakan</p>

        <stripes:messages />
        <stripes:errors />

        <p class=instr>Pilih dokumen-dokumen yang diserahkan oleh penyerah. Pastikan dokumen-dokumen mandatori disertakan.</p>

        <fieldset class=aras1>

            <stripes:form action="/kaunter/permohonan" >

                <display:table name="${actionBean.senaraiDokumen}" id="row"  class="tablecloth"  >
                    <display:column title="<input type='checkbox' id='semua' name='semua' onclick='javascript:selectAll(this);' />">
                        <stripes:checkbox name="senaraiDokumenSerahan[${row_rowNum - 1}].kodDokumen" 
                                          value="${row.kodDokumen.kod}" id="kandunganFolder${row_rowNum - 1}" 
                                          onclick="javascript:toggleSelectDocument(this, ${row_rowNum - 1});" />
                    </display:column>
                    <display:column title="Dokumen" >
                        ${row.kodDokumen.kod} - ${row.kodDokumen.nama} 
                    </display:column>
                    <display:column title="Bil." >
                        <stripes:text size="3" id="bilSalinan${row_rowNum - 1}" name="senaraiDokumenSerahan[${row_rowNum - 1}].bil" />
                    </display:column>
                    <display:column title="Mandatori" >
                        ${row.wajib == 'Y' ? 'Ya' : 'Tidak'}
                    </display:column>
                    <display:column title="Perlu Disahkan" >
                        ${row.perluDisah == 'Y' ? 'Ya' : 'Tidak'}    
                    </display:column>
                    <display:column title="Arahan Tamb." >
                        ${row.catatan}
                    </display:column>
                    <display:column title="No.Rujukan" >
                        <stripes:text name="senaraiDokumenSerahan[${row_rowNum - 1}].noRujukan" size="15" />
                    </display:column>
                    <display:column title="Catatan" >
                        <stripes:text name="senaraiDokumenSerahan[${row_rowNum - 1}].catatan" size="20" /> 
                    </display:column>
                </display:table>  
                <br/>
                <!-- FOR PENDAFTARAN: SAB/SBB/SWB -->
                <c:if test="${actionBean.senaraiUrusan[0].kodJabatan eq '16'}" >
                    <fieldset class="aras2">
                        <legend>Surat Amanah/Kebenaran/Wakil Kuasa yang dilampirkan</legend>
                        <p><label>Surat Amanah Baru:</label> Kuantiti <stripes:text name="suratSABKuantiti" size="2" /></p>
                        <p><label>Surat Amanah Daftar:</label> No. Perserahan <stripes:text name="suratSAD[0]" size="20" />&nbsp;
                            <stripes:text name="suratSAD[1]" size="20" />&nbsp;
                            <stripes:text name="suratSAD[2]" size="20" /></p>
                        <br/>
                        <p><label>Surat Kebenaran Baru:</label> Kuantiti <stripes:text name="suratSBBKuantiti" size="2" /></p>
                        <p><label>Surat Kebenaran Daftar:</label> No. Perserahan <stripes:text name="suratSBD[0]" size="20" />&nbsp;
                            <stripes:text name="suratSBD[1]" size="20" />&nbsp;
                            <stripes:text name="suratSBD[2]" size="20" /></p>
                        <br/>
                        <p><label>Surat Wakil Kuasa Baru:</label> Kuantiti <stripes:text name="suratSWBKuantiti" size="2" /></p>
                        <p><label>Surat Wakil Kuasa Daftar:</label> No. Perserahan <stripes:text name="suratSWD[0]" size="20" />&nbsp;
                            <stripes:text name="suratSWD[1]" size="20" />&nbsp;
                            <stripes:text name="suratSWD[2]" size="20" /></p>
                    </fieldset>

                    <!-- FOR PENDAFTARAN: Syarikat MCL -->
                    <!--c:if test="${actionBean.senaraiUrusan[0].kodUrusan eq 'SMD' || actionBean.senaraiUrusan[0].kodUrusan eq 'SMBT'}"-->
                        <fieldset class="aras2">
                            <legend>Bilangan Syarikat MCL / No Syarikat MCL</legend>
                            <p><label>Bilangan Syarikat MCL:</label> Kuantiti <stripes:text name="SMKuantiti" size="2" /></p>
                            <p><label>No Syarikat MCL:</label> No. Perserahan <stripes:text name="smPerserahan[0]" size="20" />&nbsp;
                                <stripes:text name="smPerserahan[1]" size="20" />&nbsp;
                                <stripes:text name="smPerserahan[2]" size="20" /></p>
                        </fieldset>
                    <!--/c:if-->
                    <!-- END FOR PENDAFTARAN: Syarikat MCL -->
                </c:if>
                <c:set scope="request" var="kodDokumenNotRequired" value="${actionBean.kodDokumenNotRequired}" />
                <br/>
                <fieldset class="aras2">
                    <legend>Dokumen-dokumen tambahan</legend> 
                    Dokumen-dokumen tambahan yang diserahkan. Pilih Kod Dokumen atau masukkan Catatan.
                    <display:table name="${actionBean.senaraiDokumenTamb}" id="row1"  class="tablecloth"  >
                        <display:column title="Pilih" >
                            <stripes:select name="senaraiDokumenTamb[${row1_rowNum - 1}].kodDokumen" 
                                            style="width:600px;">
                                <stripes:option value="0">Pilih ...</stripes:option>
                                <stripes:options-collection collection="${kodDokumenNotRequired}" 
                                                            label="nama" value="kod" />
                            </stripes:select>
                        </display:column>
                        <display:column title="Catatan" >
                            <stripes:text name="senaraiDokumenTamb[${row1_rowNum - 1}].catatan" size="50" /> 
                        </display:column>
                    </display:table>  
                </fieldset>


                <p><label>&nbsp;</label>
                    <stripes:submit name="Step6a" value="Kembali" class="btn" />
                    <stripes:submit name="Cancel" value="Batal" class="btn" />
                    <c:choose>
                        <c:when test="${actionBean.senaraiUrusan[0].kodUrusan eq 'SMB' || actionBean.senaraiUrusan[0].kodUrusan eq 'SMK'}" >
                            <stripes:submit name="Step4" value="Seterusnya" class="btn" />
                        </c:when>
                        <c:otherwise>
                            <stripes:submit name="Step3" value="Seterusnya" class="btn" />      
                        </c:otherwise>
                    </c:choose>
                </p>

            </fieldset>
        </stripes:form>

    </body>
</html>