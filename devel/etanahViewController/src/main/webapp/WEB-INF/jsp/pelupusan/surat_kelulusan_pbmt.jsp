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
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_pbmt?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
      }

    function cariPopup2(){
        // alert("search:"+index);
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn_pbmt?showFormCariKodSekatan';
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
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Syarat - Syarat Hakmilik
            </legend>
            <table align="center" border="0">
            <tr> <%--n9--%>
                <td id="tdLabel">Luas Diluluskan:</td>
                <td> <s:text name="hakmilikPermohonan.luasDiluluskan" id="luas" formatPattern="#,###,##0.0000" size="10"/>
                 <s:select name="kodUnitLuas.kod" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}">
                    <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodUOMByLuas}" label="nama" value="kod" />
                 </s:select></td>
            </tr><%--n9--%>
            <%--<p>  melaka
                <label>Jenis Hakmilik :</label>
                <s:select id="hakmilikPermohonan.kodHakmilik.nama" name="jenisHakmilik">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select>
            </p>   melaka--%>
            <tr>
                <td id="tdLabel">Tempoh Hakmilik :</td>
                <td><s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun</td>
            </tr>
            <%--<p>  n9
                <label>Jenis Hakmilik Sementara :</td>
                 <s:text name="hakSementara" id="hakmilikPermohonan.kodHakmilik.nama" size="40"/>
            </p>
            <p>
                <label>Jenis Hakmilik Tetap :</label>
                 <s:select name="hakmilikTetap" id="hakmilikTetap">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod"/>
                 </s:select>
            </p>   n9--%>
            <tr>
                <td id="tdLabel">Kadar Cukai :</td>
                <td><s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="50"/></td>
                
            </tr>
            <%--<p>
                <label>Kadar Premium :</label>
                RM &nbsp; <s:text name="ukurSen" size="10"/> &nbsp; semeter persegi
            </p>  
            <p>
                <label>Denda Premium :</label>
                RM &nbsp; <s:text name="ukurRM" size="10"/> &nbsp; semeter persegi
            </p>--%>
          <%--  <p>
                <label>Kadar Bayaran Upah Ukur dan Batu Sempadan :</label>   Mengikut Jadual
                <s:text name="kbUpah" size="20"/>
            </p>  --%>
            <%--<p><br>
                <label>Kadar Bayaran Pendaftaran dan Penyediaaan Hakmilik Sementara / Tetap:</label>
                Mengikut Peraturan - Peraturan tanah Negeri
                <s:text name="kbDaftar" size="40"/>
            </p>   n9--%>
            <tr>
                <td id="tdLabel">Kategori Tanah:</td>
                <td><s:select name="kodKategori" value="${actionBean.kodKategori}">
                        <s:option>Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                </s:select></td>
            </tr>   <%--n9--%>
            <%--<p>
                <label>Hasil :</label>
                <s:text name="hasil" size="40"/>
            </p>--%>
            <%--<p>
                <label>Bayaran Ukur (RM) :</label>
                <s:text name="bayaranUkur" size="40"/>
            </p>--%>
          <%--  <p>
                <label>Jenis Penggunaan Tanah :</label>
                <s:select name="kegunaanTanah" id="kegunaanTanah">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKegunaanTanah}" label="nama" value="kod"/>
                </s:select>
            </p>--%>
           <%-- <tr>
                            <td width="20%" style="color: #003194; font-weight: 700">Syarat Nyata </td>
                            <td ><b> : </b></td>
                            <td>
                                    <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                                    <s:hidden name="kod" id="kod"/><br>
                                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td>
                        </tr>

       <tr>
           <td width="20%" style="color: #003194; font-weight: 700">Sekatan Kepentingan </td>
           <td ><b> : </b></td>
           <td><s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
               <s:hidden name="kodSktn" id="kodSktn"/><br>
           <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td>

       </tr>--%>
       
                <tr>
                    <td id="tdLabel">Syarat Nyata :</td>
                <td><s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.kod}--${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                    <s:hidden name="kod" id="kod"/><br>
                                    <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/></td>
                             
                            </tr>
        
            
            <tr>
                <td id="tdLabel">Sekatan Kepentingan :</td>
                <td> <s:textarea name="sekatKpntgn" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.kod}--${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                    <s:hidden name="kodSktn" id="kodSktn"/><br>
           <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/></td>
            </tr>
            <%--<p>  melaka
                <label>Syarat Am :</label>
                <s:text name="syaratAm" size="40"/>
            </p>
            --%>
            </table>
        </fieldset>
    </div>
            <div align="center">
        <tr>
            <td>&nbsp;</td>

            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
        

        </tr>
    </div>
</s:form>
