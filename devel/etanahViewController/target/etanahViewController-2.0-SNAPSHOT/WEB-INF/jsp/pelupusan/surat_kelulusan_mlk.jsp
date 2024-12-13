<%-- 
    Document   : surat_kelulusan_melaka
    Created on : Mar 4, 2011, 11:40:07 AM
    Author     : Rohans
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

    function cariPopup(index){
       // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/surat_kelulusan?search&index='+index ;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=500");
    }

     function cariPopup2(index){
       // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/surat_kelulusan?showFormCariKodSekatan&index='+index ;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }

    function test(){
        var a = "hi" ;

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
<s:form beanclass="etanah.view.stripes.pelupusan.SuratKelulusanMlkActionBean" name="surat">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                SYARAT - SYARAT HAKMILIK
            </legend>
            <table border="0"  align="center" width="75%" cellspacing="5">
            <tr>
                <td id="tdLabel" width="18%">a)Jenis Hakmilik </td>
                <td id="tdLabel"><b> : </b></td>
                <td> <s:select name="kodHmlk" >
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select></td>
            </tr>
            <tr>
                <td id="tdLabel">b)Tempoh</td>
                <td id="tdLabel"><b> : </b></td>
                <td><s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun</td>
            </tr>
         
 
               <tr>
                   <td id="tdLabel">c)Hasil </td>
                    <td id="tdLabel"><b> : </b></td>
                   <td> RM<s:text name="hakmilikPermohonan.cukaiPerMeterPersegi" size="5"/>  bagi tiap-tiap 100 m.p atau Sebahagian daripandanya </td>
            </tr>
            <tr>
                <td id="tdLabel">d)Bayaran Ukur (RM) </td>
                 <td id="tdLabel"><b> : </b></td>
                <td>Mengikut P.U.(A) 438</td>
            </tr>
            
            <tr>
                <td id="tdLabel">e)Jenis Penggunaan Tanah </td>
                 <td id="tdLabel"><b> : </b></td>
                <td><s:select name="kodKategori" id="kodKategoriTanah">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                </s:select></td>
            </tr>


                <tr>
                    <td id="tdLabel">f)Syarat Nyata </td>
                     <td id="tdLabel"><b> : </b></td>
                    <c:set value="0" var="i"/>
                    <td><s:text name="kod" id="kod${i}" size="10" readonly="yes" class="tooltips" title="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"/>
                               <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup(${i})"/></td>
                </tr>


            <tr>
                <c:set value="0" var="x"/>
                <td id="tdLabel">g)Sekatan Kepentingan </td>
                 <td id="tdLabel"><b> : </b></td>
                <td><s:text name="kodSktn" id="kodSktn${x}" size="10" readonly="yes" class="tooltips" title="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"/>
                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2(${x})"/></td>
        </tr>

         </table>
        </fieldset>

    </div>
    <div>
        <tr></tr><tr></tr>
        <table border="0"  align="right" width="75%" cellspacing="5">

        <tr>
             &nbsp;

            <td> <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;</td>

        </tr>
         </table>
    </div>

</s:form>
