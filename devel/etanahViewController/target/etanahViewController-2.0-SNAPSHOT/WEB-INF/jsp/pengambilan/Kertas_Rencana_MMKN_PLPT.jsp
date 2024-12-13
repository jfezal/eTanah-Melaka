<%--
    Document   : Kertas_Rencana_MMKN
    Created on : June 24, 2011, 4:59:55 PM
    Author     : Rajesh
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!--<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>-->
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script language="javascript" type="text/javascript">





    function kemaskiniTajuk(val) {
//        alert("woi");
        var idHakmilik = $('#hakmilik').val();
//        alert("id kandungan = " + val);
        var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?kemaskiniMMKN&idKandungan=' + val;
        window.open(url, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes");
    }

    function deletKertasKand(val) {

        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?deletKertasKand&idKandungan=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function tambah(val) {
        var idHakmilik = $('#bil').val();
//        alert(idHakmilik);
        val = idHakmilik;
//        alert("val" + val);
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?TambahPerihalPemohon&bil=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function tambah3(val) {
        var idHakmilik = $('#bil3').val();
//        alert(idHakmilik);
        val = idHakmilik;
//        alert("val" + val);
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?TambahPerihalPemohon&bil=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function tambah4(val) {
        var idHakmilik = $('#bil4').val();
        val = idHakmilik;
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?TambahPerihalPemohon&bil=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function tambah5(val) {
        var idHakmilik = $('#bil5').val();
        val = idHakmilik;
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?TambahPerihalPemohon&bil=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }
    function tambah6(val) {
        var idHakmilik = $('#bil6').val();
        val = idHakmilik;
        if (confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/kertas_rencana_mmkn_plpt?TambahPerihalPemohon&bil=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                    }, 'html');
        }
    }

    function save(event, f) {
//        alert("woi");
//        if (doValidation()) {
//            doBlockUI();
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.ajax({
            type: "POST",
            url: url,
            dataType: 'html',
            data: q,
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
            },
            success: function(data) {
                $('#page_div', opener.document).html(data);
//                    $.unblockUI();
//                    self.close();
            }
        });
    <%--$.post(url,q,
    function(data){
        $('#page_div',opener.document).html(data);
        $.unblockUI();
        self.close();
    },'html');--%>
//        }
        }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.KertasRencanaMMKNPLPTActionBean" name="form2">
    <s:messages/>
    <s:errors/>
    <%--<c:if test="${form1}">--%>
    <s:hidden name="form1" value="${form1}"/>
    <s:hidden name="formPtg" value="false"/>
    <div class="subtitle">
        <fieldset class="aras1" id="locate">
            <div class="content" align="center">
                &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                       <th colspan="200" width ="500" height="30">MESYUARAT JAWATANKUASA MMKN  </th> 
                            <!--<td colspan="10"><center><b></b></center></td>-->  
                   
                </table>
                <table>
                    <tr><td> &nbsp;</td></tr>
                    <tr><td align="left"><b><label><font color="black">KERTAS BIL :</font></label><s:text name="permohonanKertas.tajuk"  size="10" style="text-align:left" id="kertasBil" class="normal_text"/>/${actionBean.permohonan.untukTahun}</b></td></tr>
                    <tr><td align="left"><b><label><font color="black">TARIKH SIDANG :</font></label><s:text name="permohonanKertas.tarikhSidang" id="tarikhMesyuarat" class="datepicker" size="30" style="text-align:left" /></b></td></tr>
                    <tr><td align="left"><b><label><font color="black">TARIKH SAH :</font></label><s:text name="permohonanKertas.tarikhSahKeputusan" id="tarikhSah" class="datepicker" size="30" style="text-align:left" /></b></td></tr>
                    <tr><td align="left"><b><label><font color="black">TEMPAT      :</font></label><s:textarea name="permohonanKertas.tempatSidang" cols="50" rows="5" style="text-align:left" id="tempat" class="normal_text"/></b></td></tr>
                </table>
            </div>
            <center>
                <s:hidden name="kertasTahun" value="${actionBean.permohonan.untukTahun}"/>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>   
            </center>
        </fieldset>
    </div>
            
    <tr><td><b><s:hidden name="heading" class="normal_text"/>
                <div class="content" align="center">
                    <!--<font style="text-transform: uppercase"><b>Permohonan Perlanjutan Tempoh Pajakan DI LOT ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.luas}, ${actionBean.hakmilik.bandarPekanMukim.nama}, DAERAH ${actionBean.hakmilik.daerah.nama}.</b></font>-->
                </div>




                <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="200" width ="500" height="30">Tujuan : </th> 
                            <td width="1000" >
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil1}"
                                               cellpadding="0" cellspacing="0" id="line">
                                    <%--<display:column title="Sub Tajuk" sortable="true">${line_rowNum}</display:column>--%>
                                    <display:column property="kandungan" title="" class="kandungan" />
                                    <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Kemaskini' border='1' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                            </td>
                        </table>
                    </div>
                </fieldset>

                <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">Perihal Permohonan : </th> 
                            <td width="1000" height ="2">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil2}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>
                                        <display:column property="kandungan" title="" class="kandungan" />
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="deletKertasKand('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                            </td>
                        </table>
                        <center>
                            <s:hidden name="bil" id="bil" value="2"/>
                            <s:button name="TambahPerihalPemohon" id="save" value="Tambah" class="btn" onclick="tambah('')"/>
                        </center>
                    </div>
                </fieldset>


                <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">Perihal Tanah : </th> 
                            <td width="1000">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil3}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>>
                                        <display:column property="kandungan" title="" class="kandungan" />
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="deletKertasKand('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>

                                </display:table>
                            </td>


                        </table>
                        <center>
                            <s:hidden name="bil3" id="bil3" value="3"/>
                            <s:button name="TambahPerihalPemohon" id="save" value="Tambah" class="btn" onclick="tambah3('')"/>
                        </center>


                    </div>
                </fieldset>
                <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">ULASAN  ADUN KAWASAN : </th> 
                            <td width="1000" height ="2">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil4}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>
                                    <display:column property="agensi.nama" title="Adun" class="kandungan" />
                                        <display:column property="kandungan" title="kandungan" class="kandungan" />
                                        
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                    
                                </display:table>
                            </td>


                        </table>
                      

                    </div>      
                </fieldset>
                        
                        <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">ULASAN  JABATAN TEKNIKAL : </th> 
                            <td width="1000" height ="2">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil7}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>
                                        
                                        <display:column property="agensi.nama" title="Jabatan Teknikal" class="kandungan" />
                                        <display:column property="kandungan" title="kandungan" class="kandungan" />
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                  
                                </display:table>
                            </td>


                        </table>
                    

                    </div>      
                </fieldset>
                        
                        <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">PERAKUAN PENTADBIR TANAH DAERAH : </th> 
                            <td width="1000" height ="2">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil5}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>
                                        <display:column property="kandungan" title="" class="kandungan" />
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="deletKertasKand('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                            </td>


                        </table>
                        <center>
                            <s:hidden name="bil5" id="bil5" value="5"/>
                            <s:button name="TambahPerihalPemohon" id="save" value="Tambah" class="btn" onclick="tambah5('')"/>  
                        </center>

                    </div>      
                </fieldset>
                        <fieldset class="aras1"> 
                    <div class="content" align="left" id="vertical-2">
                        <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                            <th colspan="100" width ="150" height="30">PERAKUAN PENGARAH TANAH DAN GALIAN : </th> 
                            <td width="1000" height ="2">
                                <display:table style="width:100%;" class="tablecloth" name="${actionBean.senaraiKertasKandunganBil6}"
                                               cellpadding="0" cellspacing="0" id="line">

                                    <display:column title="Bil." sortable="true"><center>${line_rowNum}</center></display:column>
                                        <display:column property="kandungan" title="" class="kandungan" />
                                        <display:column title="Kemaskini">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="kemaskiniTajuk('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                    <display:column title="Hapus">
                                        <div align="center">
                                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                                 id='rem_${line_rowNum}' onclick="deletKertasKand('${line.idKandungan}')" onmouseover="this.style.cursor = 'pointer';">
                                        </div>
                                    </display:column>
                                </display:table>
                            </td>


                        </table>
                        <center>
                            <s:hidden name="bil6" id="bil6" value="6"/>
                            <s:button name="TambahPerihalPemohon" id="save" value="Tambah" class="btn" onclick="tambah6('')"/>  
                        </center>

                    </div>      
                </fieldset>

<!--                <table  width="100%" border="2" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                    <tfoot>
                        <tr>    
                            <td colspan="10"><center><b>Perakuan PTD</b></center></td>  
                        </tr> 
                    </tfoot>
                </table>
                <br>
                <table  style="width:100%;" class="tablecloth" cellpadding="0" cellspacing="0" id="vertical-2">
                    <th colspan="100" width ="150" height="30">Ulasan dan Keputusan PTD : </th> 
                    <td width="1000">
                        ${actionBean.mohonFasa.keputusan.nama} 
                    <li>${actionBean.mohonFasa.ulasan} </li> <td>
                </table>-->



                <p align="center">
                    <s:hidden name="count4" id="count4" value="${actionBean.count4}"/>
                    <s:hidden name="temp4" id="temp4" value="${actionBean.count4}"/>
                    <s:hidden name="idKertas" value="${permohonanKertas.idKertas}"/>
                    <%--<s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>--%>
                </p>
                <%--</c:if>--%>
            </s:form>
