<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle"></div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<script  language="javascript" >

function updateTotal(inputTxt){
    var total = 0;
    for (var i = 0; i <5; i++){
    	var a = document.getElementById('amaun' + i)
    	if (isNaN(a.value)){
    	    alert("Nombor tidak sah");
    	    return;
        }
        total += parseFloat(a.value);
    }
    var t = document.getElementById('jumCaraBayar');
    t.value = total.toFixed(2);
    inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
}

</script>

<s:messages />
<s:errors />
<s:form beanclass="etanah.view.stripes.KutipanHasilActionBean">
<s:hidden name="akaun.noAkaun"/>
<s:hidden name="hakmilik.idHakmilik"/>
<s:hidden name="${actionBean.jumCaraBayar}"/>
<p>
    <label>
        <div class="instr"><b>Langkah 3: Cara Bayaran</b></div>
    </label>
</p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Jumlah Yang Perlu Dibayar</legend>
            <p>
                <label>Amaun :</label>
                <fmt:formatNumber value="${actionBean.baki}" currencySymbol="RM " type="currency"/>
            </p><br>
        </fieldset>
    </div>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Cara Bayaran</legend>
                <br>
                <div align="center">
                    <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloth">
                        <display:column title="Cara" >
                            <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                    id="senaraiCaraBayaran${row_rowNum - 1}" >
                                <s:option value="0" label="Pilih ..." />
                                <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" />
                            </s:select>
                        </display:column>

                        <display:column title="Bank" >
                            <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" >
                                <s:option label="Pilih..." value="0" />
                                <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                            </s:select>
                        </display:column>

                        <display:column title="Cawangan" >
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" />
                        </display:column>

                        <display:column title="No. Rujukan" >
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="20" />
                        </display:column>

                        <display:column title="Amaun (RM)" >
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}" />
                        </display:column>

                            <display:footer>
                                <tr>
                                    <td colspan="4"><div align="right"><b>Jumlah (RM):</b></div></td>
                                    <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12" class="number"/></td>
                                <tr>
                            </display:footer>

                    </display:table >
                </div>
                <br>
            </fieldset>
        </div>

    <table border="0" bgcolor="green" width="100%">
        <tr>
            <td align="right">
                <%--<s:button name="save" id="save" value="Tamat Dan Cetak Resit" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>--%>
                <s:submit name="save" value="Bayar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                <%--<s:reset name=" " value="Isi Semula" class="btn"/>--%>
                <s:submit name="kembali" value="Kembali" class="btn"/>
            </td>
        </tr>
    </table>
</s:form>

<script language="javascript" >
    var senaraiCaraBayaran0 = document.getElementById('senaraiCaraBayaran0');
    for (var i = 0; i < senaraiCaraBayaran0.length; i++){
        if (senaraiCaraBayaran0.options[i].value == 'T'){
        	senaraiCaraBayaran0.selectedIndex = i;
        	break;
        }
    }

</script>