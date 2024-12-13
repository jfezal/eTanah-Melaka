<%-- 
    Document   : kertas_pertimbanganNS
    Created on : Nov 3, 2010, 12:09:10 PM
    Author     : Murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">


    #tdLabel {
        color:black;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:250px;
        margin-right:0.5em;
        text-align:right;
        width:30em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">
    $(document).ready( function(){
        $('#noruj').blur(function()
        {
            if( $(this).val().length == 0 ) {
                alert("Sila masukkan bilangan kertas.");
                document.getElementById("noruj").value = "${actionBean.noruj}";
            }
        });
    });
    function test(f) {
        $(f).clearForm();
    }
</script>

<s:form id="form1" beanclass="etanah.view.strata.KertasPertimbanganPBBDActionBean">

    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <table>
                    <tr>
                        <td>                            
                            <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
                                <center><b>KERTAS KELULUSAN PENGARAH TANAH DAN GALIAN <br>NEGERI SEMBILAN BIL. <s:text id="noruj" name="noruj" size="7"/>/${actionBean.yr} MENGIKUT SEKSYEN 9 AKTA HAKMILIK STRATA 1985</b></center>
                                    </c:if>
                                    <c:if test="${actionBean.stageId ne 'g_sediakertasptg'}">
                                <center><b>KERTAS KELULUSAN PENGARAH TANAH DAN GALIAN <br>NEGERI SEMBILAN BIL. ${actionBean.noruj}/${actionBean.yr} MENGIKUT SEKSYEN 9 AKTA HAKMILIK STRATA 1985</b></center>
                                    </c:if>
                        </td>
                    </tr>
                </table>
            </div>

            <br>

            <p>
                <label>Pemohon/Pemaju : </label>
                ${actionBean.pemohon.pihak.nama} &nbsp;                
            </p>

            <br>            

            <p>
                <label>Butiran Hakmilik : </label>
                ${actionBean.butiranhakmilik} &nbsp;                
            </p>

            <br>

            <p>
                <label>Luas Tanah : </label>
                ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} &nbsp;
            </p>

            <br>

            <p>
                <label>Jenis Kegunaan Tanah : </label>
                ${actionBean.hakmilik.kegunaanTanah.nama} &nbsp;                
            </p>

            <br>

            <p>
                <label>Sekatan Kepentingan : </label>
            <table>
                <tr>
                    <td>                        
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null && actionBean.hakmilik.sekatanKepentingan.sekatan ne '-'}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq '-'}"> &nbsp;</c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> &nbsp; </c:if>
                    </td>
                </tr>
            </table>
            </p>

            <br/>

            <p>
                <label>Nama  Bangunan : </label>  
                ${actionBean.hp.hakmilik.bandarPekanMukim.nama}, ${actionBean.hp.hakmilik.daerah.nama} mengandungi : &nbsp;
                <c:if test="${actionBean.jumB != 0 }">                    
                    <br/> <label> &nbsp; </label>   ${actionBean.bilB}) ${actionBean.jumB} Blok Bangunan yang mengandungi ${actionBean.blokPetak} Petak                     
                </c:if>
                <c:if test="${actionBean.jumP != 0}">                    
                    <br/> <label> &nbsp; </label>   ${actionBean.bilP}) ${actionBean.jumP} Blok Bangunan Sementara yang mengandungi ${actionBean.totalP} Petak                     
                </c:if>  
                <c:if test="${actionBean.lpps != 0}">                      
                    <br/> <label> &nbsp; </label> ${actionBean.bilL}) ${actionBean.lpps} Petak Tanah &nbsp;
                </c:if>
            </p>

            <br/>

            <p>
                <label>Bilangan Petak : </label>
                ${actionBean.totalPetak}&nbsp;
            </p>

            <br>

            <p>
                <label>Harta Bersama : </label>
            <table>
                <tr>
                    <td>
                        <c:if test="${actionBean.harta eq null}">&nbsp;</c:if>
                        <c:if test="${actionBean.harta ne null}">${actionBean.harta}</c:if>
                        </td>
                    </tr>
                </table>
                </p>

                <br>

                <p>
                    <label>Tarikh Memohon Hakmilik Strata : </label>               
                ${actionBean.tarikhMohon}  &nbsp;               
            </p>

            <br>

            <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
                <p>
                    <label>Ulasan JUPEM NS : </label>
                    <s:textarea name="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" class="normal_text"/>
                </p>
            </c:if>

            <c:if test="${actionBean.stageId eq 'perakuan'}">
                <p>
                    <label>Ulasan JUPEM NS : </label>
                    <s:textarea name="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" readonly="true" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Ulasan TPTG(Pd) : </label>
                    <s:textarea name="asaspertimbangan" cols="80" rows="7" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Syor TPTG(Pd) : </label><s:radio name="syorTPTG" value="OG"></s:radio> Sokong
                    <s:radio name="syorTPTG" value="TO"></s:radio> Tidak Sokong
                    </p>
                    <br>
            </c:if>

            <c:if test="${actionBean.stageId eq 'keputusan'}">
                <p>
                    <label>Ulasan JUPEM NS : </label>
                    <s:textarea name="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" readonly="true" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Ulasan TPTG(Pd) : </label>
                    <s:textarea name="asaspertimbangan" cols="80" rows="7" readonly="true" class="normal_text"/>
                </p>
                <br>
                <p>
                    <label>Syor TPTG(Pd) : </label><s:radio name="syorTPTG" value="OG" disabled="true"></s:radio> Sokong
                    <s:radio name="syorTPTG" value="TO" disabled="true"></s:radio> Tidak Sokong
                    </p>
            </c:if>            

            <br><br>

            <c:if test="${actionBean.stageId eq 'g_sediakertasptg' || actionBean.stageId eq 'perakuan'}">
                <center>   
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
                        <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    </c:if>
                </center>
            </c:if>
        </fieldset>
    </div>
</s:form>