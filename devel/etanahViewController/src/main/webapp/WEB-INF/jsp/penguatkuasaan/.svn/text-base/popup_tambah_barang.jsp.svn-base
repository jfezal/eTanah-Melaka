<%-- 
    Document   : popup_tambah_barang
    Created on : Sept 18, 2012, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">

    .tablecloth{
        padding:0px;
        width:90%;
    }

    .infoLP{
        float: right;
        font-weight: bold;
        color:#003194;
        font-family:Tahoma;
        font-size: 13px;

    }
</style>
<script type="text/javascript">
    $(document).ready( function(){
        var bil =  ${fn:length(actionBean.senaraiPembelian)};
        for (var i = 0; i < bil; i++){
            returnCurrency($("#nilaiJualan"+i).val(),i);
        }
        
        jenisPengenalan();
        totalAmount();

    });

    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageBarangOperasi();
            self.close();
        },'html');

    }

    function validateForm(){
               
        return true;
    }

    function test(f) {
        $(f).clearForm();
    }


    function convertText(r,t){
        var i = r.value;
        document.getElementById(t).value=i.toUpperCase();
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }

    function removeNonNumeric( strString )
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

    function limitText(limitField, limitNum) {
        if (limitField.value.length > limitNum) {
            limitField.value = limitField.value.substring(0, limitNum);
        }
    }
    
    function returnCurrency(amount, i){
        //        alert(i);
        document.getElementById('nilaiJualan'+i).value = CurrencyFormatted(amount);
    }
    
     
    function returnCurrency1(amount){
        //        alert(i);
        document.getElementById('jumCaraBayar').value = CurrencyFormatted(amount);
    }

    function CurrencyFormatted(amount)
    {
        var i = parseFloat(amount);
        if(isNaN(i)) { i = 0.00; }
        var minus = '';
        if(i < 0) { minus = '-'; }
        i = Math.abs(i);
        i = parseInt((i + .005) * 100);
        i = i / 100;
        s = new String(i);
        if(s.indexOf('.') < 0) { s += '.00'; }
        if(s.indexOf('.') == (s.length - 2)) { s += '0'; }
        s = minus + s;
        return s;
    }
    
    
    function validatePoskodLength(value){
        var plength = value.length;
        if(plength != 5){
            alert('Poskod yang dimasukkan salah.');
            $('#poskod').val("");
            $('#poskod').focus();
        }
    }
    
    function jenisPengenalan(){
        if($('#pengenalan').val() == 'B'){
            document.getElementById("noPengenalanBaru").style.visibility = 'visible';
            document.getElementById("noPengenalanBaru").style.display = '';
            $('#noPengenalanLain').hide();

        }else{
            $('#noPengenalanLain').show();
            document.getElementById("noPengenalanBaru").style.visibility = 'hidden';
            document.getElementById("noPengenalanBaru").style.display = 'none';

        }
    }
    
    function totalAmount(){

        var total = 0;
        var bil = ${fn:length(actionBean.senaraiPembelian)};
        for (var i = 0; i < bil; i++){
            var amount = document.getElementById('nilaiJualan'+i).value;
            if(amount !=""){
                total += parseFloat(amount);
    <%--alert("total : "+total);--%>
                    document.getElementById('jumCaraBayar').value=total;
                }
            }
            
            returnCurrency1(document.getElementById('jumCaraBayar').value);
        }

 

 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.MaklumatPembeliBarangRampasanActionBean">
    <s:messages />
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembeli
            </legend>

            <c:if test="${edit}">
                <div class="content">
                    <p>
                        <label>Nama  :</label>
                        <s:text name="namaPembeli" id="namaPembeli" size="40" maxlength="50"/>&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="kodPengenalanPembeli.kod"  value="${actionBean.permohonan.kodPengenalanPenerima.kod}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliLain" id="noPengenalanPenerimaLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliBaru" id="noPengenalanPenerimaBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamatPembeli1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                        &nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskodPembeli" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNegeriPembeli.kod"  style="width:139px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <legend>
                        Barang Jualan
                    </legend>
                    <div class="content" align="center">
                        <display:table name="${actionBean.listOp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Maklumat Laporan Operasi">
                                <table width="10%" cellpadding="1">
                                    <tr>
                                        <td width="20%"><font class="infoLP">Id Operasi :</font></td>
                                        <td width="20%"><u><a class="popup" onclick="viewRecordOP(${line.idOperasi})">${line.idOperasi}</a></u></td>
                                    </tr>
                                    <tr>
                                        <td width="20%"><font class="infoLP">Tarikh laporan :</font></td>
                                        <td width="20%">
                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhOperasi}"/></td>
                                    </tr>
                                    <tr>
                                        <td width="20%"><font class="infoLP">Masa laporan :</font></td>
                                        <td width="20%">
                                            <fmt:formatDate pattern="hh:mm" value="${line.tarikhOperasi}"/>
                                            <fmt:formatDate pattern="aaa" value="${line.tarikhOperasi}" var="time"/>
                                            <c:if test="${time eq 'AM'}">PAGI</c:if>
                                            <c:if test="${time eq 'PM'}">PETANG</c:if>
                                            </td>
                                        </tr>

                                    </table>
                            </display:column>
                            <display:column title="Maklumat Barang Jualan">
                                <c:set value="1" var="count"/>
                                <c:if test="${fn:length(line.senaraiBarangRampasan) ne 0}">
                                    <table width="100%" cellpadding="1">
                                        <tr>
                                            <th  width="1%" align="center"><b>Bil</b></th>
                                            <th  width="1%" align="center"><b>Barang</b></th>
                                            <th  width="1%" align="center"><b>Amaun</b></th>
                                            <th  width="1%" align="center"><b>Status</b></th>
                                        </tr>
                                        <c:forEach items="${line.senaraiBarangRampasan}" var="barang">
                                            <c:if test="${barang.statusJual != 'Y'}">
                                                <tr>
                                                    <td width="5%">${count}</td>
                                                    <td width="50%"><u><a class="popup" onclick="viewRecord(${barang.idBarang})">${barang.item}</a></u></td>
                                                    <td width="50%">
                                                        <s:text id="nilaiJualan${line_rowNum-1}${count-1}" name="nilaiJualan${line_rowNum-1}${count-1}" formatPattern="0.00" onblur="returnCurrency(this.value,'${line_rowNum-1}${count-1}');" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                                                    </td>
                                                    <td width="50%">
                                                        <s:checkbox name="pilihBarang${line_rowNum-1}${count-1}" id="pilihBarang${line_rowNum-1}${count-1}" value="${barang.idBarang}"/>
                                                    </td>
                                                </tr>

                                                <c:set value="${count+1}" var="count"/>
                                            </c:if>

                                        </c:forEach>
                                    </table>
                                </c:if>
                            </display:column>
                        </display:table>
                        <br>
                    </div>


                </div>
                <p align="center">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:hidden name="idOp"/>
                    <s:hidden name="idBarangOperasi"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                </p>
                <br/><br/>
            </c:if>

            <c:if test="${editPembelian}">
                <s:hidden name="idKompaun" id="idKompaun"/>
                <div class="content">
                    <p>
                        <label>Nama  :</label>
                        <s:text name="namaPembeli" id="namaPembeli" size="40" maxlength="50"/>&nbsp;
                    </p>
                    <p>
                        <label>Jenis Pengenalan :</label>
                        <s:select name="kodPengenalanPembeli.kod"  value="${actionBean.permohonan.kodPengenalanPenerima.kod}"  style="width:139px;" id="pengenalan" onchange="jenisPengenalan()">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodJenisPengenalan}" label="nama" value="kod" sort="nama" />
                        </s:select>
                        &nbsp;
                    </p>
                    <p id="noPengenalanLain">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliLain" id="noPengenalanPenerimaLain" maxlength="20" onkeyup="this.value=this.value.toUpperCase();" />
                        &nbsp;
                    </p>
                    <p id="noPengenalanBaru" style="visibility: hidden; display: none">
                        <label>No.Pengenalan :</label>
                        <s:text name="noPengenalanPembeliBaru" id="noPengenalanPenerimaBaru" maxlength="12" onkeyup="validateNumber(this,this.value);" onchange="validateKPLength(this.value);"/>
                        <font color="red" size="1">cth : 850510075342 </font>
                        &nbsp;
                    </p>
                    <p>
                        <label>Alamat :</label>
                        <s:text name="alamatPembeli1"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>
                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli2"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli3"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                    </p>
                    <p>
                        <label> &nbsp;</label>
                        <s:text name="alamatPembeli4"  size="30" maxlength="50" onkeyup="this.value=this.value.toUpperCase();"/>

                        &nbsp;
                    </p>
                    <p>
                        <label>Poskod :</label>
                        <s:text name="poskodPembeli" id="poskod" size="19" maxlength="5" onkeyup="validateNumber(this,this.value);" onchange="validatePoskodLength(this.value);"/>

                    </p>
                    <p>
                        <label>Negeri :</label>
                        <s:select name="kodNegeriPembeli.kod"  style="width:139px;">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" sort="nama" />
                        </s:select>
                    </p>
                    <br>
                    <legend>
                        Barang Jualan
                    </legend>
                    <div class="content" align="center">
                        <display:table name="${actionBean.senaraiPembelian}" id="line" class="tablecloth" cellpadding="0" cellspacing="0">
                            <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                            <display:column title="Barang">
                                <c:forEach items="${actionBean.listOp}" var="l">
                                    <c:forEach items="${l.senaraiBarangRampasan}" var="br">
                                        <c:if test="${br.idBarang == line.rujukan1}">
                                            ${br.item}<br>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </display:column>
                            <display:column title="Amaun">
                                <input id="nilaiJualan${line_rowNum-1}" value="${line.amaun}" name="nilaiJualan${line_rowNum-1}" formatPattern="#,##0.00" onblur="returnCurrency(this.value,'${line_rowNum-1}');" onkeyup="validateNumber(this,this.value);"/>&nbsp;
                            </display:column>
                            <display:footer>
                                <tr>
                                    <td colspan="2" align="right">Jumlah (RM):</td>
                                    <td><s:text name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" formatPattern="0.00"
                                            class="number" readonly="readonly" style="background:transparent;border:0px;"/></td>
                                <tr>
                                </display:footer>
                            </display:table>
                        <br>
                    </div>


                </div>
                <p align="center">
                    <s:hidden name="idPermohonan" value="${actionBean.permohonan.idPermohonan}"/>
                    <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
                    <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="editBarangBelian" value="Simpan"/>
                    <s:submit class="btn"  value="Tutup" name="closePopup" onclick="self.close();" />
                </p>
                <br/><br/>
            </c:if>

            <c:if test="${view}">
                <div class="content">

                </div>
            </c:if>

        </fieldset>
    </div>


</s:form>

