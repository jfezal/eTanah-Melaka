<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<table width="100%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Kutipan Cukai</font>
            </div>
        </td>
    </tr>
</table>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
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
<script type="text/javascript">
    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/kutipan_hasil?showEditPemohon&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    function remove(f, id){
        var queryString = $(f).formSerialize()
        alert("Hapus ID Hakmilik : "+id+"?");
        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&"+queryString+"idHakmilik="+id;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        });
    }

    <%--function remove(id){

        var url = "${pageContext.request.contextPath}/hasil/kutipan_hasil?delete&idHakmilik="+id;
        document.form.action = url;
        document.form.submit();

    }--%>

    function change(){
        for (var i = 0; i < 5; i++){
        var a = document.getElementById('senaraiCaraBayaran'+i);
        if (a.value == 'T'){
            $('#hapus'+i).hide();
            $("#hapus1"+i).hide();
            $("#hapus2"+i).hide();
            $("#hapus3"+i).hide();
        }
        <%-- else if((a.value == 'CL')||(a.value == 'CB')||(a.value == 'CT')){
            $('#hapus'+i).show();
            $('#hapus1'+i).show();
            $('#hapus2'+i).show();
            $('#hapus3'+i).show();
        }--%>
        else{
            $('#hapus'+i).show();
            $('#hapus1'+i).show();
            $('#hapus2'+i).show();
            $('#hapus3'+i).show();
            }
        }
    }

</script>
<s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean">
    <s:hidden name="${actionBean.list}" />
    <s:hidden name="${actionBean.hakmilikList}" />
    <%--<s:hidden name="${actionBean.account}" />--%>
    <s:hidden name="hakmilik.idHakmilik" />
    <s:hidden name="akaun.noAkaun" />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran</legend>
            <p>
                <div class="content" align="center">
                    <%--<c:if test="${actionBean.list gt '1'}">
                        <s:button name="" value="Tambah" class="btn" disabled="true" style="display:${actionBean.flag}"/><br>
                    </c:if>--%>
                    <display:table class="tablecloth" name="${actionBean.list}" pagesize="5" cellpadding="0" cellspacing="0" requestURI="/hasil/kutipan_hasil" id="line">
                        <%--<display:column title="<input type='checkbox' name='semua' onclick='javascript:selectAll(this);' />">
                            <s:checkbox name="" id="kandunganFolder${line_rowNum - 1}"/>
                        </display:column>--%>
                        <display:column title="Bil.">
                            <div align="center">
                                ${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum-1}" value="${line.hakmilik.idHakmilik}"/>
                            </div>
                        </display:column>
                        <display:column  title="ID Hakmilik" >
                            <a href="#" onclick="edit('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="noAkaun" title="Nombor Akaun" class="akaun"/>
                        <display:column property="baki" title="Amaun (RM)" format="{0,number, 0.00}" style="text-align:right;"/>
                        <c:if test="${actionBean.del}">
                            <display:column title="Hapus">
                                <div align="center">
                                    <%--<s:button name="delete" value="${line.hakmilik.idHakmilik}" onclick="remove(this.form, '${line.hakmilik.idHakmilik}');" />--%>
                                    <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="remove(this.form, '${line.hakmilik.idHakmilik}');" />
                                </div>
                            </display:column>
                        </c:if>
                        <display:footer>
                            <tr>
                                <td colspan="3" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <em><font size="1">**</font>&nbsp;<font size="1" color="black">Sila klik pada ID Hakmilik untuk melihat maklumat terperinci.</font></em>

                </div>
            </p>
            <p>
                <label >Jumlah Yang Perlu Dibayar :</label>&nbsp;
                <fmt:formatNumber value="${actionBean.jumlahCaj}" currencySymbol="RM " type="currency"/>
            </p>
            <br>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Cara Bayaran</legend>
            <p class=instr><em><font color="black">Masukkan butir-butir pembayaran.<br>&nbsp;
                    <font color="red">PERINGATAN:</font> Tidak dibenarkan menggunakan cara pembayaran yang lain
                    bersama dengan Cek dan hanya satu Cek dibenarkan.</font></em>
            </p>
            &nbsp;&nbsp;&nbsp;&nbsp;<s:errors field="amaun"/>
            <div align="center">
                <display:table name="${actionBean.senaraiCaraBayaran}" id="row" class="tablecloth">
                    <display:column title="Cara" class="tunai">
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                  id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(this)">
                            <s:option value="0" label="Pilih ..." />
                            <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" />
                        </s:select>
                    </display:column>

                    <display:column title="Bank" >
                        <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="hapus${row_rowNum - 1}">
                            <s:option label="Pilih..." value="0" />
                            <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                        </s:select>
                    </display:column>

                    <display:column title="Cawangan" >
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" id="hapus1${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="No. Rujukan" >
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" id="hapus2${row_rowNum - 1}" size="20" />
                    </display:column>

                    <display:column title="Tarikh">
                        <s:text name=" " id="hapus3${row_rowNum - 1}" size="20" readonly="true" maxlength="10" class="datepicker"/>
                    </display:column>

                    <display:column title="Amaun (RM)">
                        <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                onblur="javascript:updateTotal(this);" id="amaun${row_rowNum - 1}"/>
                    </display:column>
                    <display:footer>
                        <tr>
                            <td colspan="5"><div align="right"><b>Jumlah (RM):</b></div></td>
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
                <s:submit name="back" value="Kembali" class="btn"/>&nbsp;
                <s:submit name="main" value="Batal" class="btn" style="display:${actionBean.flag}"/>&nbsp;
                <s:submit name="save" value="Bayar" class="btn" disabled="${actionBean.visible}"/>
            </td>
        </tr>
    </table>
</s:form>
<%--<script language="javascript" >
    var senaraiCaraBayaran0 = document.getElementById('senaraiCaraBayaran0');
    for (var i = 0; i < senaraiCaraBayaran0.length; i++){
        if (senaraiCaraBayaran0.options[i].value == 'T'){
        	senaraiCaraBayaran0.selectedIndex = i;
                senaraiCaraBayaran0.setAmaun = ${actionBean.jumlahCaj};

        	break;
        }
    }
</script>--%>
<script language="javascript" >
	$(document).ready(function() {
	    // set the first default payment to Tunai
	    $('#senaraiCaraBayaran0').val('T');
	    // focus on the first payment
	    $('#amaun0').val(${actionBean.jumlahCaj});
            $('#jumCaraBayar').val(${actionBean.jumlahCaj});
            $("#hapus0").hide();
            $("#hapus10").hide();
            $("#hapus20").hide();
            $("#hapus30").hide();
    });
</script>