<%-- 
    Document   : draf_MMK
    Created on : May 21, 2010, 9:52:33 AM
    Author     : nurul.izza
    Refer to ulasan_tanah_ladang_melaka
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<script type="text/javascript">
    $(document).ready( function(){
        $(".uppercase").bestupper();
        $(".wideselect")
        .mouseover(function(){
            $(this).data("origWidth", $(this).css("width")).css("width", "auto");
        })
        .mouseout(function(){
            $(this).css("width", $(this).data("origWidth"));
        });
        filterKodSeksyen();
        $("#cariKodSyaratNyata").click(function(){
            var url ="${pageContext.request.contextPath}/pendaftaran/maklumat_asas?showFormCariKodSyaratNyata";
            window.open(url,"eTanah","status=1,toolbar=0,location=1,menubar=0,scrollbars=yes,resizable=1,width=900,height=600");
        });
        $("#luas").keyup(function(){
            validNumber($('#luas').val(),'luas');
            dodacheck($('#luas').val());
        });


    });
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.UlasanMMKMlkActionBean">
    <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
    <s:messages/>
    <s:errors/>
    
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tajuk</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Tajuk Kertas</td></tr>
                <tr><td></td><td>
                        <s:textarea name="tajuk" rows="4" style="width:97%;"/>                 
                 </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Ulasan Jabatan - Jabatan Teknikal</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Jabatan Pertanian, Negeri Melaka</td></tr>
                <tr><td></td><td>
                        <s:textarea name="ulasanJabatanPertanian" rows="4" style="width:97%;" class="normal_text"/>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Jabatan Tenaga Kerja, Negeri Melaka</td></tr>
                <tr><td></td><td>
                        <s:textarea name="ulasanJabatanTenagaKerja" rows="4" style="width:97%;" class="normal_text"/>                                             
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Ulasan YB ADUN Kawasan</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td></td><td>
                            <s:textarea name="ulasanJabatanPertanian" rows="4" style="width:97%;" class="normal_text"/>
                </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
            </table>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>Perakuan Pentadbir Tanah Daerah Melaka Tengah</legend>
            <table width="100%">
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td></td><td>
                            <s:textarea name="syorPtg" rows="4" style="width:97%;" class="normal_text" readonly="true"/>
                      </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td></td><td>
                            <s:textarea name="syorPtg" rows="4" style="width:97%;" class="normal_text" readonly="true"/>
                      </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Syarat - Syarat Hakmilik</td></tr>
            </table>
            <br/>
            <p>
            <label> Jenis Hakmilik :</label>
                <s:select name="jenisHakmilik" id="jenisHakmilik">
                    <s:option value="0">Sila Pilih</s:option>
                    <s:options-collection collection="${list.senaraiKodHakmilik}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
            <label> Tempoh :</label>
                <s:text name="tempoh"/> tahun
            </p>
            <p>
                <label> Premium :</label>  1/200 <b> x</b> Harga Pasaran <b> x </b> Tempoh Pajakan
            </p>
            <p>
            <label> Hasil :</label>
                RM <s:text name="hasil"/> bagi tiap - tiap 100mp. atau sebahagian daripadanya.
                (RM  <s:text name="hasilHektar"/> bagi setiap hektar)
            </p>
            <p>
                <label> Bayaran Ukur :</label>   Mengikut P.U.(A) 438
            </p>
            <p>
                <label> Syarat Nyata :</label>
                <s:text name="kodSyaratNyata" id="syaratNyata" readonly="true" /><s:button name="cariKodSyaratNyata" value="Cari" id="cariKodSyaratNyata" class="btn" />
            </p>    
            <p>
                <label> Sekatan Kepentingan :</label>Tanah ini tidak boleh dipindahmilik atau dipajak kecuali dengan kebenaran Pihak Berkuasa Negeri<br/>
            </p>
            <p align="center" valign="top">
                <font color="blue"><b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Penjenisan :  &nbsp;&nbsp;&nbsp;</b></font> <textarea name="penjenisan" rows="4" style="width:40%;" cols="40"  id="penjenisan"></textarea><br/><br/>
                <font color="blue"><b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                Syarat Am  :  &nbsp;&nbsp;&nbsp;</b></font> <textarea name="syaratAm" rows="4" style="width:40%;" cols="40"  id="syaratAm"></textarea>
            </p>   
            <br/><br/>
            <p>
                <label>&nbsp;</label>
                <c:if test="${ptg}">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
                <c:if test="${ulasan}">
                    <s:button name="simpanDisable" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
        </fieldset>
    </div>
</s:form>