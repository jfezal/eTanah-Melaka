<%-- 
    Document   : kertas_pertimbanganPHPP_NS
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
    function hakdetails (val) {
        var noruj = $("#noruj").val();
        var ulasanJabatan = $("#ulasanJabatanUkurdanPemetaan").val();    
        doBlockUI();
        var url = '${pageContext.request.contextPath}/strata/kertasPHPP?hakdetails&idMh=' + val+'&noruj='+noruj+'&ulasanJabatan='+ulasanJabatan;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

    function hakdetails1 (val) {           
        var noruj = $("#noruj").val();
        var ulasanJabatan = $("#ulasanJabatanUkurdanPemetaan").val();
        var asaspertimbangan = $("#asaspertimbangan").val();
        var syorTPTG;
        if(document.getElementById('syorTPTG1').checked) {
            syorTPTG="OG";
        }
        if(document.getElementById('syorTPTG2').checked) {
            syorTPTG="TO";
        }            
        doBlockUI();
        var url = '${pageContext.request.contextPath}/strata/kertasPHPP?hakdetails&idMh=' + val+'&noruj='+noruj+'&ulasanJabatan='+ulasanJabatan+'&asaspertimbangan='+asaspertimbangan+'&syorTPTG='+syorTPTG;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
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

<s:form beanclass="etanah.view.strata.KertasPertimbanganPHPPActionBean">

    <s:messages/>
    <s:errors/>

    <div class="subtitle" align="center">
        <fieldset class="aras1">            
            <table><tr><td> 
                        <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
                            <center><b>KERTAS KELULUSAN PENGARAH TANAH DAN GALIAN <br>NEGERI SEMBILAN BIL. <s:text id="noruj" name="noruj" size="7"/>/${actionBean.yr} MENGIKUT SEKSYEN 9 AKTA HAKMILIK STRATA 1985</b></center>
                                </c:if>
                                <c:if test="${actionBean.stageId ne 'g_sediakertasptg'}">
                                    <s:hidden id="noruj" name="noruj"/>
                            <center><b>KERTAS KELULUSAN PENGARAH TANAH DAN GALIAN <br>NEGERI SEMBILAN BIL. ${actionBean.noruj}/${actionBean.yr} MENGIKUT SEKSYEN 9 AKTA HAKMILIK STRATA 1985</b></center>
                                </c:if>
                    </td></tr></table>
        </fieldset>
    </div> <br />

    <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
        <div class="subtitle">
            <p>
                <label>Senerai Hakmilik Terlibat : </label>
                <s:select name="idMh" onchange="hakdetails(this.value);" id="idMh">
                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                        <s:option value="${item.id}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
        </div>
    </c:if>
    <c:if test="${actionBean.stageId ne 'g_sediakertasptg'}">
        <div class="subtitle">
            <p>
                <label>Senarai Hakmilik Terlibat : </label>
                <s:select name="idMh" onchange="hakdetails1(this.value);" id="idMh">
                    <c:forEach items="${actionBean.senaraiHakmilikPermohonan}" var="item" varStatus="line">
                        <s:option value="${item.id}" style="width:400px">
                            ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                        </s:option>
                    </c:forEach>
                </s:select>
            </p>
        </div>
    </c:if>
    <br />

    <div id="hak">
        <p>
            <label>Pemohon/Pemaju : </label>
            ${actionBean.pemohon.pihak.nama} &nbsp;
        </p>
        <br>

        <p>
            <label>Butiran Hakmilik Tanah : </label>
            ${actionBean.butiranhakmilik} &nbsp;
        </p>
        <br>

        <p>
            <label>Butiran Hakmilik : </label>
            No Bangunan : ${actionBean.hakmilik.noBangunan} /&nbsp;
            No Tingkat  : ${actionBean.hakmilik.noTingkat} /&nbsp;
            No Petak    : ${actionBean.hakmilik.noPetak}
        </p>
        <br>
        
        <p>
            <label>Luas Petak : </label>
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
        <table><tr><td>
                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null && actionBean.hakmilik.sekatanKepentingan.sekatan ne '-'}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq '-'}">Tiada Data &nbsp;</c:if>
                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                    </td></tr></table>
        </p>
        <br>

        <p>
            <label>Nama Bangunan : </label>
        ${actionBean.mohonbngn.nama} Blok Bangunan Yang mengandungi ${actionBean.mohonbngn.bilanganPetak} Petak di ${actionBean.hp.hakmilik.bandarPekanMukim.nama}, ${actionBean.hp.hakmilik.daerah.nama} &nbsp;
    </p>
    <br>
    <p>
        <label>Petak Aksesori : </label>
        &nbsp; <%--${actionBean.mohonbngn.bilanganPetak} Petak &nbsp; BETULKAN --%>
    </p>
    <br>

    <p>
        <label>Harta Bersama : </label>
    <table><tr><td>  &nbsp; <%--${actionBean.harta} BETULKAN --%> </td></tr></table>
</p>
<br>

<p>
    <label>Tarikh Memohon Hakmilik Strata : </label>
    <fmt:formatDate value="${actionBean.tarikhMasuk}" pattern="dd/MM/yyyy" />  &nbsp;
</p>
<br>

</div>

<div>
    <fieldset class="aras1">
        <c:if test="${actionBean.stageId eq 'g_sediakertasptg'}">
            <p>
                <label>Ulasan JUPEM NS : </label>
                <s:textarea id="ulasanJabatanUkurdanPemetaan" name="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" class="normal_text"/>                
            </p>
        </c:if>

        <c:if test="${actionBean.stageId eq 'perakuan'}">
            <p>
                <label>Ulasan JUPEM NS : </label>
                <s:textarea name="ulasanJabatanUkurdanPemetaan" id="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" readonly="true" class="normal_text"/>
            </p>
            <br>

            <p>
                <label>Ulasan TPTG(Pd) : </label>
                <s:textarea name="asaspertimbangan" id="asaspertimbangan" cols="80" rows="7" class="normal_text"/>
            </p>
            <br>

            <p>
                <label>Syor TPTG(Pd) : </label><s:radio name="syorTPTG" id="syorTPTG1" value="OG"></s:radio> Sokong
                <s:radio name="syorTPTG" id="syorTPTG2" value="TO"></s:radio> Tidak Sokong
                </p>
                <br>
        </c:if>


        <c:if test="${actionBean.stageId eq 'keputusan'}">
            <p>
                <label>Ulasan JUPEM NS : </label>
                <s:textarea name="ulasanJabatanUkurdanPemetaan" id="ulasanJabatanUkurdanPemetaan" cols="80" rows="7" readonly="true" class="normal_text"/>
            </p>
            <br>

            <p>
                <label>Ulasan TPTG(Pd) : </label>
                <s:textarea name="asaspertimbangan" id="asaspertimbangan" cols="80" rows="7" readonly="true" class="normal_text"/>
            </p>
            <br>

            <p>
                <label>Syor TPTG(Pd) : </label><s:radio name="syorTPTG" id="syorTPTG1" value="OG" disabled="true"></s:radio> Sokong
                <s:radio name="syorTPTG" id="syorTPTG2" value="TO" disabled="true"></s:radio> Tidak Sokong
                </p>
        </c:if>

        <br><br>
        <c:if test="${actionBean.stageId eq 'g_sediakertasptg' || actionBean.stageId eq 'perakuan'}">
            <center>   <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></center>
        </c:if>
    </fieldset>
</div>
</s:form>