
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
 <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
        
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

    function tambahBaru(h) {
        var url = '${pageContext.request.contextPath}/uam/verify_user?sahPenyelia&idPguna=' + pgunatex + '&status=' + typeSTS + '&catatan=' + tex;
        document.location.href = url;
        alert('value h ++> ' + h);
        window.open("${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/terima_aduan?hakMilikPopup", "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function tambah(idMohon,f) {
        var q = $(f).formSerialize();
//        var idMohon;
//alert(idMohon);
//        if(idMohon==""){
            var url = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/terima_aduan?generatIdMohon&idPermohonan='+idMohon;
        $.get(url, q,function (data) {
//            alert(data);
            idMohon = data;
            document.location.href = '${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/terima_aduan?showForm&idPermohonan='+idMohon;
//            alert(idMohon);
        window.open("${pageContext.request.contextPath}/pengambilan/aduan_kerosakan/terima_aduan?hakMilikPopup&idPermohonan="+idMohon, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }, 'html');
        
        
    }

    function refreshPageHakmilik() {
        var url = '${pageContext.request.contextPath}/pengambilan/terima_aduan';
        $.get(url,
                function (data) {
                    $('#page_div').html(data);
                }, 'html');
    }

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.aduan.TerimaAduanActionBean">
    <fieldset class="aras1">

        <div id="hiddenPenyerah">
            <legend>Maklumat Pengadu</legend>
            <p>
                <label>Nama</label><s:text name="nama" id="penyerahNama" size="42" onblur="doUpperCase(this.id)"/>
            </p>
            <p>
                <label><em>*</em>No. Pengenalan :</label>
                <s:select name="kod" value="" >
                    <s:option value="">Pilih Jenis...</s:option>
                    <s:option value="B">No K/P Baru  </s:option>
                    <s:option value="S">No Syarikat  </s:option>
                    <s:option value="L">No K/P Lama  </s:option>
                    <s:option value="P">No Pasport  </s:option>
                    <s:option value="T">No Tentera  </s:option>
                    <s:option value="I">No Polis  </s:option>
                    <s:option value="0">Tidak Berkenaan  </s:option>
                    <s:option value="N">No Bank  </s:option>
                    <s:option value="F">No Paksa  </s:option>
                    <s:option value="U">No Pertubuhan  </s:option>
                    <s:option value="D">No Pendaftaran  </s:option>
                    <s:option value="Z">Badan Kerajaan  </s:option>
                    <s:option value="X">Tiada  </s:option>
                </s:select>

                <s:text name="noKp" id="penyerahNoPengenalan" size="15" value=""/>
                <em>[cth: 780901057893]</em>

            </p>

            <p>
                <label><em>*</em>Alamat</label>
                <s:text name="alamat1" id="penyerahAlamat1" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat2" id="penyerahAlamat2" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <s:text name="alamat3" id="penyerahAlamat3" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Bandar</label>
                <s:text name="alamat4" id="penyerahAlamat4" size="30" onblur="doUpperCase(this.id)"/>
            </p>

            <p>
                <label>Poskod</label>
                <s:text name="poskod" maxlength="5" id="penyerahPoskod" size="17" onkeyup="validateNumber(this,this.value);" />
                <em>5 Digit [cth : 12000]</em>
            </p>

            <p>
                <label><em>*</em>Negeri</label>
                <%--penyerahNegeri.kod--%>
                <s:select name="negeri" id="penyerahNegeri" >
                    <s:option value="0">Pilih ...</s:option>
                    <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                </s:select>
                <%--<s:submit  name="updatePenyerah" value="Kemaskini Penyerah" class="longbtn"/>--%>
            </p>
            <p>
                <label>No.Telefon</label>
                <s:text name="noTel" id="penyerahNoTelefon" size="15"/>
            </p>
            <p>
                <label>Email</label>
                <s:text name="email" id="penyerahEmail" size="50"/>
            </p>
<!--
            <p>
                <label>Hubungan</label>
                <s:text name="hubungan" id="penyerahEmail" size="50"/>
            </p>-->

        </div>  

        <p></p>
        <p></p>
    </fieldset>              




    <p></p>


    <fieldset class="aras1">
        <legend>
            Maklumat Tanah Pengadu
        </legend>


        <p align="center">
            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="30" cellpadding="0" cellspacing="0"
                           requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                <display:column title="No" sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                <%--<display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>--%>
                <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                    <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                    <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                </display:column>
                <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                <display:column title="Bandar/Pekan/Mukim" style="text-transform : capitalize">${line.hakmilik.bandarPekanMukim.nama} ${fn:toLowerCase(line.hakmilik.seksyen.nama)}</display:column>

                <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
        </display:table>
    </p> 
    <p align="center">
        <s:hidden name="idPermohonan"/>
        <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambah('${actionBean.idPermohonan}',this.form);"/>
    </p>
</fieldset>


<p></p>


<fieldset class="aras1">
    <legend>
        Maklumat Aduan
    </legend>
    <p align="center">
    <table >

        <tr> 
            <td >
                <table >
                    <tr>

                        <td >Perihal Aduan :</td>
                        <td >
                            <s:textarea id="aduan" name="aduan"  rows="10" cols="50"/> 

                        </td>
                        </p>
                    </tr>    
                </table>
            </td></tr></table>
</p>

<p></p>
<p></p>





</fieldset>



<p align="center">
    <s:submit name="simpan" id="kemas" value="Simpan" class="btn"/>&nbsp;
    <s:submit name="selesai" value="Selesai" class="btn"/>&nbsp;
</p>
</s:form>
