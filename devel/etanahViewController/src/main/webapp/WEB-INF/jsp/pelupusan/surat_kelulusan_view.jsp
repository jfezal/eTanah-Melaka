<%-- 
    Document   : surat_kelulusan
    Created on : May 20, 2010, 12:02:36 PM
    Author     : nurul.izza(modified by afham)
    modified on 03092010
--%>

<%@include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<link type="text/css" rel="stylesheet"
      href="${pageContext.request.contextPath}/pub/styles/jquery.tooltip.css"/>
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
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">

    function cariPopup(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }

    <%-- function test(a){
       
         if(a.value == "" || a.value == null){
            alert("Hi");
            return false ;
         }
       
     }
    --%>


</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanActionBean" name="surat">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" align="center">
        <fieldset class="aras1">
            <legend>
                Syarat - Syarat Hakmilik
            </legend>
            <table align="center" border="0" width="70%">
                <tr> <%--n9--%>
                    <td id="tdLabel">Luas Diluluskan:</td>
                    <td>${actionBean.hakmilikPermohonan.luasDiluluskan} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</td>
                </tr>
                <tr>
                    <td id="tdLabel">Tempoh Hakmilik :</td>
                    <td>${actionBean.hakmilikPermohonan.tempohHakmilik} &nbsp; tahun</td>
                </tr>
                <tr>
                    <td id="tdLabel">Kadar Cukai :</td>
                    <td>${actionBean.hakmilikPermohonan.keteranganCukaiBaru}</td>
                </tr>
                <tr>
                    <td id="tdLabel">Kategori Tanah:</td>
                    <td>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                </tr>
                <tr>
                    <td id="tdLabel">Syarat Nyata :</td>
                    <td>${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                </tr>
                <tr>
                    <td id="tdLabel">Sekatan Kepentingan :</td>
                    <td> ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                </tr>
            </table>
        </fieldset>
    </div>
   
</s:form>
