<%--
    Document   : surat_peringatan_notis6A_2
    Created on : Jan 6, 2010, 9:46:17 AM
    Author     : nurfaizati
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">

    function popup(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=1");
    }

    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?saveDasar&idDasar="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function remove1(iddasar,id){
        if(confirm("Adakah pasti untuk hapus Id Hakmilik :"+id+" ?")){
            var IdHakmilik = $("#idHakmilik").val();
            var Daerah = $("#kodDaerah").val();
            var BandarPekanMukim = $("#bandarPekanMukim").val();
            var NoLot = $("#noLot").val();
            var NoHakmilik = $("#noHakmilik").val();
            var KategoriTanah = $("#kategoriTanah").val();
            var AmaunDari = $("#amaunDari").val();
            var DariTahun = $("#dariTahun").val();
            var HinggaTahun = $("#hinggaTahun").val();
            var url = '${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?delete&idHakmilikRemove='+id+'&idDasar='+iddasar;
            url += '&idHakmilik='+IdHakmilik;
            url += '&daerah='+Daerah;
            url += '&bandarPekanMukim='+BandarPekanMukim;
            url += '&noLot='+NoLot;
            url += '&noHakmilik='+NoHakmilik;
            url += '&kategoriTanah='+KategoriTanah;
            url += '&amaunDari='+AmaunDari;
            url += '&dariTahun='+DariTahun;
            url += '&hinggaTahun='+HinggaTahun;
            $.get(url,
            function(data){
                $('#daerah').html(data);
            },'html');
        }
    }


    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function validateMoney(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumericMoney(content);
            return;
        }
    }

    function RemoveNonNumericMoney( strString )
    {
        var strValidCharacters = "1234567890.";
        var strReturn = "";
        var strBuffer = "";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

    function filterDaerah(iddasar,kodDaerah, f){
        var url = '${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?penyukatanBPM&daerah='+kodDaerah+'&idDasar='+iddasar;
             f.action = url;
             f.submit();
        }
    function filterBPM(iddasar,kodBPM, frm) {
                 var daerah = $('#daerah').val();
                 var url = '${pageContext.request.contextPath}/hasil/surat_peringatan_notis6A?penyukatanSeksyen&bandarPekanMukim=' + kodBPM + '&daerah' + daerah;
                 frm.action = url;
                 frm.submit();
               }    

    function validateYearDari(){
        if($('#dariTahun').val() != '' && $('#hinggaTahun').val() == ''){
            alert('Sila masukkan Tahun Hingga terlebih dahulu.');
            $('#hinggaTahun').focus();
            return false;
        }else if($('#dariTahun').val() == '' && $('#hinggaTahun').val() != ''){
            alert('Sila masukkan Tahun Dari terlebih dahulu.');
            $('#dariTahun').focus();
            return false;
        }else if($('#dariTahun').val() != '' && $('#hinggaTahun').val() != ''){
            var dari = parseInt($('#dariTahun').val());
            var hgga = parseInt($('#hinggaTahun').val());
            if(dari > hgga){
                alert('Tahun Dari dimasukkan tidak sesuai.');
                $('#dariTahun').val('');
                $('#dariTahun').focus();
                return false;
            }else
                return true;
        }else
            return true;
    }

    function validateYearLn(id){
        var length = $('#'+id).val().length;
        if(length > 0 && length < 4){
            alert('Tahun yang dimasukkan salah.');
            $('#'+id).val('');
            $('#'+id).focus();
            return false;
        }else
            return true;
    }
    function completeId(id) {
        var l = id.length;
        if (l > 0) {
          var lengthId = 8;
          var i = "";
          for (var x = 0; x < (lengthId - l); x++) {
            i = i + '0';
          }
          var noHakmilik = i + id;
          $("#noHakmilik").val(noHakmilik);
        }
      }
    
</script>

<div id="daerah">
    <s:form beanclass="etanah.view.stripes.hasil.SuratPeringatanNotis6AActionBean" id="form">
        <div class="subtitle">
            <s:hidden name="idDasar" id="IdDasar"/>
            <s:messages/>
            <s:errors/>&nbsp;
            <fieldset class="aras1">
                <legend>
                    Kriteria Hakmilik-hakmilik Terlibat
                </legend>
                <div class="instr-fieldset">
                    <font color="red">PERINGATAN:</font>Sila Masukkan Maklumat Berikut


                </div>&nbsp;
                <p>
                    <label> ID Hakmilik :</label>
                    <s:text name="idHakmilik" id="idHakmilik" maxlength="20" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>  Daerah :</label>
                    <s:select id="kodDaerah" name="daerah" style="width:210px;" onchange="filterDaerah('${actionBean.dasarTuntutanCukai.idDasar}',this.value, this.form);">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodDaerah}" label="nama" value="kod"  sort="nama"/>
                        <%--<c:forEach items="${listUtil.senaraiKodDaerah}" var="i" >--%>
                       <%--< <s:option value="${i.kod}">${i.kod} - ${i.nama}</s:option>--%>
                       <%--< </c:forEach>--%>
                    </s:select>
                </p>

                <p>
                    <label>  Bandar/Pekan/Mukim :</label>
                    <s:select id="bandarPekanMukim" name="bandarPekanMukim" style="width:210px;" onchange="filterBPM('${actionBean.dasarTuntutanCukai.idDasar}',this.value, this.form);">
                        <s:option value="">--Sila Pilih--</s:option>
                       <s:options-collection collection="${actionBean.senaraiBPM}" label="nama" value="kod" sort="nama" />
                       <%--<c:forEach items="${actionBean.senaraiBPM}" var="i" >--%>
                        <%--<s:option value="${i.kod}">${i.bandarPekanMukim} - ${i.nama}</s:option>
                         </c:forEach>--%>
                    </s:select>
                </p>
                
                <p>
                    <c:if test="${actionBean.kodNegeri eq 'melaka'}">
            <label>  Seksyen :</label>
            <s:select name="seksyen" id="seksyen" style="width:210px;">
              <s:option value="">--Sila Pilih--</s:option>
              <c:forEach items="${actionBean.senaraiKodSeksyen}" var="i" >
                <s:option value="${i.kod}">${i.nama}</s:option>
              </c:forEach>
            </s:select>
                    </p></c:if>
          
                <p>
                    <label>Nombor Lot/PT :</label>
                    <s:text id="noLot" name="noLot" maxlength="15" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Nombor Hakmilik :</label>
                    <s:text id="noHakmilik" name="noHakmilik" maxlength="8" size="31" onblur="this.value=this.value.toUpperCase();"/>
                </p>
                <p>
                    <label>Kategori Tanah :</label>
                    <s:select id="kategoriTanah" name="kategoriTanah" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p>
                   <p>
                    <label>Bangsa :</label>
                    <s:select id="bangsa" name="bangsa" style="width:210px;">
                        <s:option value="">--Sila Pilih--</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodBangsa}" label="nama" value="kod" sort="nama" />
                    </s:select>
                </p><%--
                --%>
            <%--       <p>
                        <label>Bangsa :</label>
                        <s:select name="bangsa.nama">
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option>Melayu</s:option>
                            <s:option>Cina</s:option>
                            <s:option>India</s:option>
                            <s:option>Orang Asli</s:option>
                            <s:option>Siam</s:option>
                            <s:option>Lain-lain</s:option>
                        </s:select>
                    </p>--%>
                <p>
                    <label>Amaun Patut Kutip(RM) :</label>
                    Dari <s:text id="amaunDari" name="amaunDari"  size="8" maxlength="10" onkeyup="validateMoney(this,this.value);"/> <%--Hingga <s:text name="amaunHingga" id="" size="8" maxlength="10" onkeyup="validateMoney(this,this.value);"/>--%>
                </p>
                <p>
                    <label> Tahun Tunggakan :</label>
                    Dari <s:text id="dariTahun" name="dariTahun" size="8" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearLn(this.id);"/> Hingga <s:text name="hinggaTahun" id="hinggaTahun" size="8" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearLn(this.id);"/> <%--Tahun--%>
                </p><br>
                <p align="right">
                    <s:submit name="search" value="Cari" class="btn" onclick="return validateYearDari();" />
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('form');"/>
                </p>
            </fieldset>
        </div><br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Senarai Yang Dicadangkan Untuk Surat Peringatan/Notis 6A
                </legend>
                <p>
                    <label >ID Dasar :</label>
                    ${actionBean.dasarTuntutanCukai.idDasar}&nbsp;
                </p>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.list1}" cellpadding="0" cellspacing="0"
                                   requestURI="/hasil/surat_peringatan_notis6A" id="line" excludedParams="delete idHakmilikRemove popup">
                        <display:column title="Bil" sortable="true">${line_rowNum}</display:column>

                        <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.idHakmilik}');return false;">${line.idHakmilik}</a></display:column>
                        <display:column property="noLot" title="No Lot/PT" style="text-align:right" />
                        <display:column property="noHakmilik" title="Nombor Hakmilik" style="text-align:right"/>
                        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                        <display:column property="seksyen.nama" title="Seksyen"  />
                        <display:column property="daerah.nama" title="Daerah" />
                        <display:column property="kategoriTanah.nama" title="Kategori Tanah" />
                            <%--<c:set value="0" var="bakiTunggakan"/>
                            <c:forEach items="${line.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                <c:if test="${(transaksi.kodTransaksi.kod eq '61402' or transaksi.kodTransaksi.kod eq '76152') and transaksi.untukTahun ne actionBean.currentYear}">
                                    <c:set value="${(transaksi.amaun + bakiTunggakan)}" var="bakiTunggakan"/>
                                </c:if>
                            </c:forEach>
                        <display:column title="Baki Tertunggak (RM)" style="text-align:right" >
                            <fmt:formatNumber pattern="#,##0.00" value="${bakiTunggakan}"/>
                        </display:column>--%>
                            <c:if test="${actionBean.tahun}">
                                    <c:set value="0" var="bakiTunggakan"/>
                                    <c:set value="0" var="bilTahun"/>
                                    <c:forEach items="${line.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                        <c:if test="${transaksi.untukTahun ge actionBean.dariTahun and transaksi.untukTahun le actionBean.hinggaTahun}">
                                            <c:set value="${(transaksi.amaun + bakiTunggakan)}" var="bakiTunggakan"/>
                                            <c:if test="${bilTahun eq 0}">
                                                <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                                            </c:if>
                                            <c:if test="${bilTahun > 0}">
                                                <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                                            </c:if>
                                            <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                        </c:if>
                                    </c:forEach>
                                <display:column title="Baki Tertunggak (RM)" style="text-align:right" >
                                    <fmt:formatNumber pattern="#,##0.00" value="${bakiTunggakan}"/>
                                </display:column>
                                <display:column title="Tahun Tunggakan" style="text-align:right" >
                                    <c:out value="${minTahun}"/><c:if test="${bilTahun > 1}"> - <c:out value="${maxTahun}"/></c:if>
                                </display:column>
                            </c:if>
                            <c:if test="${!actionBean.tahun}">
                                <display:column title="Baki Tertunggak (RM)" style="text-align:right" >
                                    <fmt:formatNumber pattern="#,##0.00" value="${line.akaunCukai.baki}"/>
                                </display:column>
                                <c:set value="0" var="bilTahun"/>
                                    <c:forEach items="${line.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                        <%--<c:if test="${(transaksi.kodTransaksi.kod eq '61402' or transaksi.kodTransaksi.kod eq '76152') and transaksi.untukTahun ne actionBean.currentYear}">--%>
                                            <c:if test="${bilTahun eq 0}">
                                                <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                                            </c:if>
                                            <c:if test="${bilTahun > 0}">
                                                <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                                            </c:if>
                                            <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                        <%--</c:if>--%>
                                    </c:forEach>
                                <display:column title="Tahun Tunggakan" style="text-align:right" >
                                    <c:out value="${minTahun}"/><c:if test="${bilTahun > 1}"> - <c:out value="${maxTahun}"/></c:if>
                                </display:column>
                            </c:if>
                            <%--<c:set value="0" var="bilTahun"/>
                            <c:forEach items="${line.akaunCukai.senaraiTransaksiDebit}" var="transaksi">
                                <c:if test="${(transaksi.kodTransaksi.kod eq '61402' or transaksi.kodTransaksi.kod eq '76152') and transaksi.untukTahun ne actionBean.currentYear}">
                                    <c:if test="${bilTahun eq 0}">
                                        <c:set value="${transaksi.untukTahun}" var="minTahun"/>
                                    </c:if>
                                    <c:if test="${bilTahun > 0}">
                                        <c:set value="${transaksi.untukTahun}" var="maxTahun"/>
                                    </c:if>
                                    <c:set value="${bilTahun + 1}" var="bilTahun"/>
                                </c:if>
                            </c:forEach>
                        <display:column title="Tahun Tunggakan" style="text-align:right" >
                            <c:out value="${minTahun}"/><c:if test="${bilTahun > 1}"> - <c:out value="${maxTahun}"/></c:if>
                        </display:column>--%>
                        <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                      id="catatan" onclick="remove1('${actionBean.dasarTuntutanCukai.idDasar}','${line.idHakmilik}');" />
                            </div>
                        </display:column>
                    </display:table>
                </div>
                <p align="right">
                    <s:submit   name="save" value="Simpan" class="btn" disabled="${actionBean.btn}" />
                </p>
            </fieldset>
        </div>
    </s:form>
</div>
