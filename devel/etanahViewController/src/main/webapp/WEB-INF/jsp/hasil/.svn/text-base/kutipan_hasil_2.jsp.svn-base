<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloths.css"/>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/js/jquery.form.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });

       var bil = ${actionBean.bilAmaunList};
       for(var m =0;m<parseInt(bil);m++){
           $('#amt'+m).val("0");
       }
    });
</script>
<script type="text/javascript">
    function bal(x, row){
        var akaun = document.getElementById('senaraiAkaun'+row);
        frm = this.form;
        self.opener.addRows(akaun.value);
        $.get("${pageContext.request.contextPath}/hasil/kutipan_hasil?storeNoAkaun&nomborAkaun="+akaun.value,
            setTimeout(function(){
                self.close();
            }, 100));
       
    }

    function ball(){
        var list = "";
        var bil = ${actionBean.bilAmaunList};
        for(var n =0;n<parseInt(bil);n++){
           var m = document.getElementById('amt'+n);
           list += m.value;
           if((n+1)<bil){
               list +=",";
           }
        }
        <%--alert(list);--%>
        frm = this.form;
        self.opener.addRows(list);
        $.get("${pageContext.request.contextPath}/hasil/kutipan_hasil?storeNoAkaun&nomborAkaun="+list,
        setTimeout(function(){
            self.close();
        }, 100));
    }

    function calc (val, row){
        if ((isNaN(val))||((val) =="")){
            alert("Nombor tidak sah");
            val = RemoveNonNumeric(val);
        }
        var x = parseFloat(val).toFixed(2);
        $('#amt' + (row)).val(x);
        var baki = ${actionBean.balance};
        var total = 0;
        var bil = ${actionBean.bilAmaunList};
        for(var m =0;m<parseInt(bil);m++){
            var a = document.getElementById('amt' + m)
            total += parseFloat(a.value);
        }

        var kodCaw = (document.getElementById('kodCaw')).value;
        var kodCawHm = (document.getElementById('idHm'+row)).value;
        var kod = kodCawHm.substr(2, 2);
        if(kodCaw != kod){
            alert("Bayaran Lebih tidak dibenarkan bagi Hakmilik daerah lain.");
            $('#amt' + (row)).val("0");
            return false;
        }

        var balance = parseFloat(baki) - total;
        if(total > baki){
            alert("Amaun yang dimasukkan lebih besar. Sila masukkan semula.");
            $('#amt' + (row)).val("0.00");
            var total2 = 0;
            for(var m =0;m<parseInt(bil);m++){
                var a = document.getElementById('amt' + m)
                total2 += parseFloat(a.value);
            }
            var balance2 = parseFloat(baki) - total2;
            $('#balance').val(balance2.toFixed(2));
        }else{
            $('#balance').val(balance.toFixed(2));
        }
        var m = document.getElementById('balance');
        var bal = parseFloat(m.value);
        if(bal == 0){
            $('#next').removeAttr("disabled");
        }else{
            $('#next').attr("disabled", "true");
            var total1 = 0;
            for(var m =0;m<parseInt(bil);m++){
                var a = document.getElementById('amt' + m)
                total1 += parseFloat(a.value);
            }
            var balance1 = parseFloat(baki) - total1;
            $('#balance').val(balance1.toFixed(2));
        }
    }

    function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0";
        var intIndex = 0.00;
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
</script>

<s:form beanclass="etanah.view.stripes.hasil.KutipanHasilActionBean" id="hasil">

    <s:hidden name="pguna.kodCawangan.kod}" value="${actionBean.pguna.kodCawangan.kod}" id="kodCaw"/>
    <fieldset class="aras1">
        <legend>Maklumat Akaun</legend>
        <%--<p class=instr>
            <font color="red">PERINGATAN:</font> Sila Pilih Salah Satu ID Hakmilik.
        </p>--%>
        <p>
            <label>Baki : </label>
            <%--<fmt:formatNumber value="${actionBean.balance}" currencySymbol="RM " type="currency" style="background:transparent;border:0 px;cursor:pointer"/>--%>
            <s:text name="balance" formatPattern="#,##0.00" value="${actionBean.balance}" id="balance" style="background:transparent;border:transparent;"/>
        </p>
        <div class="content" align="center">
            <display:table class="tablecloth" name="${actionBean.senaraiAkaun}" id="line1">
                <%--<display:column title="Pilih" style="text-align:center">
                    <s:radio name="idHmBalance" value="${line.noAkaun}" id="senaraiAkaun${line_rowNum - 1}" onclick="bal('${line.noAkaun}','${line_rowNum - 1}')"/>
                </display:column>--%>
                <display:column title="Bil" style="text-align:center">
                    ${line1_rowNum}.
                    <s:hidden name="${line1.hakmilik.idHakmilik}" class="x${line1_rowNum-1}" value="${line1.hakmilik.idHakmilik}" id="idHm${line1_rowNum-1}"/>
                </display:column>
                <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                    <display:column property="noAkaun" title="Nombor Akaun" style="width:300px"/>
                </c:if>
                <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="width:240px"/>
                <display:column title="Amaun (RM)">
                    <s:text name="amaunLebihList[${line1_rowNum-1}]" id="amt${line1_rowNum - 1}" size="20" style="text-align:right;"
                            onblur="calc(this.value,${line1_rowNum -1})" maxlength="8"/>
                </display:column>
            </display:table>
        </div>
        <p></p>
        <div align="center"><table style="width:99.2%" bgcolor="green">
        <tr>
            <td align="right">
                <s:button name=" " value="Seterusnya" class="btn" onclick="return ball();" id="next" disabled="true"/>&nbsp;
                <s:button name=" " value="Tutup" class="btn" onclick="self.close()" />&nbsp;
            </td>
        </tr>
    </table></div>
    </fieldset>
</s:form>
