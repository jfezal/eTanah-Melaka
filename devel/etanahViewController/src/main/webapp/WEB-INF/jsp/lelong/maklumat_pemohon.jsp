<%-- 
    Document   : maklumat_pemohon
    Created on : Mar 3, 2010, 4:41:07 PM
    Author     : mazurahayati
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<meta http-equiv="refresh" content="100">--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>

<script type="text/javascript">

    $(document).ready( function(){
        //$('#hantar').attr('onClick', 'agihTugasanPP();');
        //$('#hantar').attr("disabled", false);//to enable selesai button
        var kodNegeri = $('#kodNegeri').val();
        var idStage = $('#stageId').val();
        var kodUrusan = $('#kodUrusan').val();
        var resultCase = $('#keputusanKes').val();
        var kodUrusanTemp = $('#kodUrusanTemp').val();

        // ** START -- properties for Melaka ** //
        if(kodNegeri == '04' && idStage == 'semakPembida'){
            if(resultCase == "" || resultCase == "LS" || resultCase =="RM"){ // LS = Lelongan Seterusnya(Pentadbir Tanah)
                if(kodUrusan == 'PPJP'){
                    $('#page_id_7').hide();//hide tab agihan tugasan
                }
            }
            else if (resultCase == "LS"){
                if(kodUrusan == 'PPJP'){
                    $('#page_id_7').hide();//hide tab agihan tugasan
                }
            }  else if (resultCase == "RM"){
                if(kodUrusan == 'PPJP'){
                    $('#page_id_7').hide();//hide tab agihan tugasan
                }

            }else if (resultCase == "AA"){
                if(kodUrusan == 'PPJP'){
                    $('#page_id_7').show();//show tab agihan tugasan
                }
            }
            $('#report').hide();//hide button Jana Dokumen
            $('#hantar').removeClass('disablebtn').addClass('btn'); //to change class name
            $('#hantar').attr("disabled", false);//to enable selesai button


        }

    });

    
    function popuptambah(){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_pemohon?popuptambah", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=1200");
    }

    function popupAlamat(){
        window.open("${pageContext.request.contextPath}/lelong/maklumat_pemohon?tambahAlamat", "eTanah",
        "status=0,toolbar=0,scrollbars=1,location=0,menubar=0,width=1000,height=1200");
    }

    function removePemohon(val){
        var url = '${pageContext.request.contextPath}/lelong/maklumat_pemohon?deletePemohon&idPemohon='+val;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }

    function validate(){

        if ($('input[name=penggadai]:checked').length == 0) {
            alert("Sila Pilih Maklumat Pemohon");
            return false;
        }

        return true;
    }

</script>


<s:form beanclass="etanah.view.stripes.lelong.MaklumatPemohonLActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="keputusan" id="keputusanKes"/>
    <s:hidden name="kodNegeri" id="kodNegeri"/>
    <s:hidden name="stageId" id="stageId"/>
    <s:hidden name="permohonan.kodUrusan.kod" id="kodUrusan"/>
    <s:hidden name="permohonanNota.idMohonNota" id="permohonanNota"/>
    <s:hidden name="idPengguna" id="idPengguna"/>
    <s:hidden name="statusJanaDokumen" id="statusJanaDokumen"/>
    <s:hidden name="statusJanaDokumenRAP" id="statusJanaDokumenRAP"/>
    <s:hidden name="permohonan.rujukanUndang2" id="kodUrusanTemp"/>

    <div class="subtitle">
        <c:if test="${actionBean.display eq false}">
            <c:if test="${actionBean.pemegangGadai eq true}">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Pemohon
                    </legend><br>
                    <div class="content" align="center">
                        <p class=instr align="left">
                            *<em>Petunjuk : Sila Pilih Pemohon</em>
                        </p>
                        <display:table name="${actionBean.listPemohon}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                            <display:column title="Bil" class="rowCount">
                                ${line1_rowNum}
                            </display:column>
                            <display:column title="NO Perserahan" >
                                ${line1.permohonan.idPermohonan}
                            </display:column>
                            <display:column title="ID Hakmilik" >
                                ${actionBean.idHakmilik2}
                            </display:column>
                            <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                            <display:column property="noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                            <display:column title="Alamat" class="alamat" style="text-transform:uppercase;">

                                ${line1.alamat.alamat1}<c:if test="${line1.alamat.alamat2 ne null}">,</c:if>
                                ${line1.alamat.alamat2}<c:if test="${line1.alamat.alamat3 ne null}">,</c:if>
                                ${line1.alamat.alamat3}<c:if test="${line1.alamat.alamat4 ne null}">,</c:if>
                                ${line1.alamat.alamat4}<c:if test="${line1.alamat.poskod ne null}">,</c:if>
                                ${line1.alamat.poskod}<c:if test="${line1.alamat.negeri.kod ne null}">,</c:if>
                                ${line1.alamat.negeri.nama}
                            </display:column>

                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}" value = "PG" style="text-transform:uppercase;">
                            </display:column>
                            <display:column  title="Pilih" >
                                <div align="center">
                                    <s:checkbox name="penggadai" id="penggadai"  class="checkbox" value="${line1.permohonan.idPermohonan}" />
                                </div>
                            </display:column>

                        </display:table>
                        <p align="center"><label></label>
                            <br>
                            <s:button name="simpanPG" id="tambah" value="Simpan" class="btn" onclick="if(validate())doSubmit(this.form, this.name, 'page_div')"/>
                        </p>
                    </div>

                    <legend>
                        Pemohon Yang Di Pilih
                    </legend>
                    <div class="content" align="center">

                        <display:table name="${actionBean.senaraiPermohonanPihak3}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                            <display:column property="noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                            <display:column title="ID Hakmilik" value="${actionBean.idHakmilik2}"/>
                            <display:column title="Alamat" property="alamatSurat" class="alamat" style="text-transform:uppercase;"/>
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}" style="text-transform:uppercase;">
                            </display:column>

                        </display:table>
                        <br>
                    </div>
                </fieldset>
            </c:if>

            <c:if test="${actionBean.pemegangGadai eq false}" >
                <fieldset class="aras1">
                    <legend>
                        Maklumat Pemohon 
                    </legend>
                    <div class="content" align="center">
                        <display:table name="${actionBean.senaraiPermohonanPihak3}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">

                            <display:column title="Bil">
                                ${line1_rowNum}
                                <s:hidden name="" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                            </display:column>

                            <display:column property="nama" title="Nama" style="text-transform:uppercase;" />
                            <display:column property="noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;"/>
                            <display:column title="ID Hakmilik" value="${actionBean.idHakmilik2}"/>
                            <display:column title="Alamat" class="alamat" style="text-transform:uppercase;">

                                ${line1.alamat.alamat1}<c:if test="${line1.alamat.alamat2 ne null}">,</c:if>
                                ${line1.alamat.alamat2}<c:if test="${line1.alamat.alamat3 ne null}">,</c:if>
                                ${line1.alamat.alamat3}<c:if test="${line1.alamat.alamat4 ne null}">,</c:if>
                                ${line1.alamat.alamat4}<c:if test="${line1.alamat.poskod ne null}">,</c:if>
                                ${line1.alamat.poskod}<c:if test="${line1.alamat.negeri.kod ne null}">,</c:if>
                                ${line1.alamat.negeri.nama}

                            </display:column>
                            <display:column property="pihak.noTelefon1" title="No. Telefon" />
                            <display:column property="jenis.nama" title="Status" class="${line1_rowNum}" value = "PG" style="text-transform:uppercase;" >
                            </display:column>

                        </display:table>
                        <br>
                    </div>
                </fieldset>
            </c:if>
        </c:if>

        <c:if test="${actionBean.display eq true}">
            <fieldset class="aras1">

                <legend>
                    Maklumat Pemohon 
                </legend>

                <div class="content" align="center">

                    <display:table name="${actionBean.senaraiPermohonanPihak3}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                        <display:column title="Bil">
                            ${line1_rowNum}
                            <s:hidden name="" class="x${line1_rowNum -1}" value="${line1.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="nama" title="Nama" style="text-transform:uppercase;"/>
                        <display:column property="noPengenalan" title="No. Pengenalan" style="text-transform:uppercase;" />
                        <display:column title="ID Hakmilik" value="${actionBean.idHakmilik2}"/>
                        <display:column title="Alamat" class="alamat" style="text-transform:uppercase;">
                            ${line1.alamat.alamat1}<c:if test="${line1.alamat.alamat2 ne null}">,</c:if>
                            ${line1.alamat.alamat2}<c:if test="${line1.alamat.alamat3 ne null}">,</c:if>
                            ${line1.alamat.alamat3}<c:if test="${line1.alamat.alamat4 ne null}">,</c:if>
                            ${line1.alamat.alamat4}<c:if test="${line1.alamat.poskod ne null}">,</c:if>
                            ${line1.alamat.poskod}<c:if test="${line1.alamat.negeri.kod ne null}">,</c:if>
                            ${line1.alamat.negeri.nama}
                        </display:column>
                        <display:column property="jenis.nama" title="Status" class="${line1_rowNum}" value = "PG" style="text-transform:uppercase;">
                        </display:column>
                    </display:table>
                    <br>
                </div>
            </fieldset>

            <c:if test="${fn:length(actionBean.listAsal)>0}">
                <br>
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik
                    </legend><br>

                    <p class=instr align="left">
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <font color="red">* Petunjuk :</font><em><font color="black">Sila Rujuk IDPermohonan Baru Untuk Hakmilik Yang Telah Ada Pembida Untuk Urusan Seterusnya</font></em>
                    </p>

                    <div class="content" align="center">

                        <display:table name="${actionBean.listMohonAsal}" id="line1" class="tablecloth" requestURI="/lelong/maklumat_pemohon">
                            <display:column title="Bil">
                                ${line1_rowNum}
                            </display:column>
                            <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                            <display:column property="permohonan.idPermohonan" title="IDPermohonan Baru" />
                            <display:column property="hakmilikPermohonan.permohonan.idPermohonan" title="IDPermohonan Sebenar" />
                            <display:column title="Status" property="kodStatusLelongan.nama"/>
                            
                        </display:table>
                        <br>
                    </div>
                </fieldset>
            </c:if>
        </c:if>

        <c:if test="${fn:length(actionBean.listPermohonan)>0}">
            <fieldset class="aras1">
                <legend>
                    Sejarah Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.listPermohonan}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <display:column title="IDPermohonan" property="idPermohonan" />
                        <display:column title="Urusan" property="kodUrusan.nama" style="text-transform:uppercase;" />
                        <display:column title="Status" class="nama${line_rowNum}">
                            <c:if test="${line.status eq 'SL'}" >
                                SELESAI
                            </c:if>
                            <c:if test="${line.status eq 'BP'}" >
                                BATAL PERMOHONAN
                            </c:if>
                            <c:if test="${line.status eq 'T '}" >
                                PERMOHONAN DI TOLAK
                            </c:if>
                            <c:if test="${line.status eq 'TG'}" >
                                PERMOHONAN DI TANGGUH
                            </c:if>
                        </display:column>
                        <%--<display:column title="Ulasan" class="nama${line_rowNum}"/>--%>
                    </display:table>
                </div>
            </fieldset>
        </c:if>

    </div>
</s:form>
