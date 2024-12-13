<%-- 
    Document   : maklumat_hakmilik_tambahan_single
    Created on : Nov 2, 2009, 12:04:05 AM
    Author     : abu.mansur
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    $(document).ready(function() {
        var tahunMula = '${actionBean.tahunMulaREMTS}';
        var tempoh = '${actionBean.tempoh}';
        if(tahunMula != "" || tempoh != ""){
            var yearStart = parseInt(tahunMula);
            var yearDuration = parseInt(tempoh);
            var yearFinish = yearStart + yearDuration - 1;
            $("#tahunHingga").val(yearFinish);
        }
    });

    function Simpan() {
	if(document.hakmilik.$("#luas").value == ""){
		alert('Sila masukkan " Luas yang ditanam semula " terlebih dahulu.');
  		document.hakmilik.$("#luas").focus();
		return;
	}
        if(document.hakmilik.$("#date").value == ""){
		alert('Sila masukkan " Tarikh mula ditanam semula " terlebih dahulu.');
  		document.hakmilik.$("#date").focus();
		return;
	}
        if(document.hakmilik.$("#datepicker").value == ""){
		alert('Sila masukkan " Tarikh mula ditanam semula " terlebih dahulu.');
  		document.hakmilik.$("#datepicker").focus();
		return;
	}
        <%--if(document.hakmilik.$("#tahun").value == ""){
		alert('Sila masukkan " Pengurangan Semula Tahun Sebelum Kerana Ditanam Semula " terlebih dahulu.');
  		document.hakmilik.$("#tahun").focus();
		return;
	}--%>
    }
    
    function validateYearDari(value){
        var yearNow = new Date().getFullYear();
        if(value.length < 4){
            alert("Kemasukan tahun salah.");
            $("#tahunDari").val("");
            $("#tahunDari").focus();
            return false;
        }else if(parseInt(value) < yearNow){
            alert("Kemasukan tahun salah.");
            $("#tahunDari").val("");
            $("#tahunDari").focus();
            return false;
        }
    }

    function validateYearHingga(value){
        if(value.length < 4){
            alert("Kemasukan tahun salah.");
            $("#tahunHingga").val("");
            $("#tempoh").val("");
            $("#tahunHingga").focus();
            return false;
        }else{
            return yearCalculation();
        }
    }

    function yearCalculation(){
        if($("#tahunDari").val() != "" && $("#tahunHingga").val() != ""){
            var yearStart = parseInt($("#tahunDari").val());
            var yearFinish = parseInt($("#tahunHingga").val());
            var yearDuration = yearFinish - yearStart + 1;
            if(yearDuration < 1){
                alert("Tahun Hingga mestilah sama atau melebihi tahun Dari.");
                $("#tahunHingga").val("");
                $("#tempoh").val("");
                $("#tahunHingga").focus();
                return false;
            }else{
                $("#tempoh").val(yearDuration);
                return true;
            }
        }else{
            alert("Tahun Dari mestilah diisi terlebih dahulu.");
            $("#tahunHingga").val("");
            $("#tempoh").val("");
            $("#tahunDari").focus();
            return false;
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

    function popup(id){
        window.open("${pageContext.request.contextPath}/hasil/papar_hakmilik_pihak?showForm&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }

    function convertUOM(kod){
        var value = $("#luas").val();
        var valueAfter = 0.0;
        if(kod == "M"){ // Hektar to Meter Persegi
            valueAfter = value*10000;
            $("#luas").val(valueAfter);
        }else if(kod == "H"){ //Meter persegi to Hektar
            valueAfter = value/10000;
            $("#luas").val(valueAfter);
        }
    }

    function convertUOMns(kod){
        var value =$("#luas").val();
        var valueAfter = 0.0;
        if(kod == "H"){// Ekar to Hektar
            valueAfter = value*0.404686;
            $("#luas").val(valueAfter);
        }else if(kod == "E"){// Hektar to Ekar
            valueAfter = value/0.404686;
            $("#luas").val(valueAfter);
        }
    }
</script>

<div class="subtitle displaytag">
        <br>
    <s:form name="hakmilik" beanclass="etanah.view.stripes.hasil.MaklumatHakmilikTambahSingleActionBean">
        <c:if test="${actionBean.negeri ne 'melaka' and actionBean.syaratTanam eq 'sawit'}">
            <p class=instr align="center">
                *<em>Peringatan :</em> Permohonan remisyen hendaklah dibuat setiap tahun dalam masa 3 tahun berturut-turut.
            </p>
        </c:if>
        <c:if test="${actionBean.negeri ne 'melaka' and actionBean.syaratTanam eq 'getah'}">
            <p class=instr align="center">
                *<em>Peringatan :</em> Permohonan remisyen hendaklah dibuat setiap tahun dalam masa 6 tahun berturut-turut.
            </p>
        </c:if>
    <fieldset class="aras1">
        <legend>
            Maklumat Tanam Semula
        </legend>
            <p>
                <s:messages />
                <s:errors />&nbsp;
            </p>
        <div>
            <p>
                <label><font color="red">*</font>Luas yang ditanam semula :</label>
                <s:text name="permohonanAktiviti.luasTerlibat" id="luas" formatPattern="0.00000000" maxlength="15" onkeyup="validateNumber(this,this.value);" />&nbsp;
                <c:if test="${actionBean.negeri eq 'melaka'}">
                    <s:select name="kodLuas" onchange="convertUOM(this.value);">
                        <s:option>Pilih ...</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodUOM}" label="nama" value="kod" />
                    </s:select>
                </c:if>
                <c:if test="${actionBean.negeri ne 'melaka'}">
                    <s:select name="kodLuas" onchange="convertUOMns(this.value);">
                        <s:option>Pilih ...</s:option>
                        <s:options-collection collection="${actionBean.senaraiKodUOM}" label="nama" value="kod" />
                    </s:select>
                </c:if>                    
                <%--<s:hidden name="hakmilikPermohonan.id" />--%>
            </p>
            <P>
                <label><font color="red">*</font>Tarikh mula ditanam semula :</label>
                <s:text name="dateMula" id="datepicker" class="datepicker"/>&nbsp;<font size="1" color="red"> (cth : hh / bb / tttt)</font>
            </P>
            <p style="height:50px">
                <label><%--<font color="red">*</font>--%>Pengurangan Semula Tahun Sebelum Kerana Ditanam Semula :</label>
                <s:text name="permohonanAktiviti.tahunSebelum" id="tahun" maxlength="4" onkeyup="validateNumber(this,this.value);" />&nbsp;<font size="1" color="red"> (cth : tttt)</font>
            </p>
            <%--<p>
                <h4><strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        Tanah-tanah yang dimiliki selain tanah dipohon</strong></h4>
            </p>
            <p>
                <label>i) Jenis Nombor Hakmilik :</label>
                <s:text name="permohonanAktiviti.hakmilikLain" />
            </p>
            <p>
                <label>ii) Nombor Lot / PT :</label>
                <s:text name="permohonanAktiviti.lotLain" />
            </p>--%>
        </div>
    </fieldset>
        <br>
    <fieldset class="aras1">
        <legend>
            Tanah-tanah yang dimiliki selain tanah dipohon
        </legend>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiHakmilik}" pagesize="5" cellpadding="0" cellspacing="0"
                           requestURI="/common/maklumat_hakmilik_tambahan_single" id="line">
                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column title="ID Hakmilik"><a href="#" onclick="popup('${line.idHakmilik}');return false;">${line.idHakmilik}</a></display:column>
                <%--<display:column property="idHakmilik" title="ID Hakmilik"/>--%>
                <display:column property="daerah.nama" title="Daerah"/>
                <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                <display:column property="kategoriTanah.nama" title="Ketegori Tanah"/>
                <display:column property="syaratNyata.syarat" title="Syarat Nyata"/>
                <display:column title="Keluasan"><fmt:formatNumber pattern="#,##0.00" value="${line.luas}"/> - ${line.kodUnitLuas.nama}</display:column>
                <display:column title="Cukai Tanah Tahunan (RM)" style="text-align:right" >
                    <fmt:formatNumber pattern="#,##0.00" value="${line.cukaiSebenar}"/>
                </display:column>
            </display:table>
        </div>
    </fieldset>
    <%--<c:if test="${actionBean.negeri eq 'melaka'}">--%>
            <br>
        <fieldset class="aras1">
            <legend>
                Tempoh ${actionBean.permohonan.kodUrusan.nama}
            </legend>
                <c:if test="${actionBean.negeri ne 'melaka'}">
                    <div class="instr-fieldset">
                        <font color="red">PERINGATAN:</font> Perlu diisi oleh pihak Pentadbir Tanah sahaja.
                    </div>
                </c:if>
            &nbsp;
            <p>
                <label><c:if test="${actionBean.negeri eq 'melaka'}"><font color="red">*</font></c:if>Tempoh Tahun Remisyen :</label>
                Dari &nbsp;<s:text id="tahunDari" name="tahunMulaREMTS" size="6" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearDari(this.value);" />&nbsp;
                Hingga &nbsp;<s:text id="tahunHingga" name="tahunHingga" size="6" maxlength="4" onkeyup="validateNumber(this,this.value);" onchange="return validateYearHingga(this.value);" />&nbsp;
            </p>
            <p>
                <label>Tempoh :</label>
                <s:text id="tempoh" name="tempoh" size="4" maxlength="2" readonly="true"/> Tahun
            </p>
            <br>
        </fieldset>
    <%--</c:if>--%>
    <br>
    <div style="${actionBean.display}">
        <p align="right">
            <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
            <%--<s:reset class="btn" name="reset" value="Isi Semula"/>--%>
        </p>
    </div>
        <%--<table width="96%" style="${actionBean.display}">
        <tr>
            <td align="right">
                <div>
                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="saveOrUpdate" value="Simpan"/>&nbsp;
                    <s:reset class="btn" name="reset" value="Isi Semula"/>
                </div>
            </td>
        </tr>
        </table>--%>
    </s:form>
</div>
