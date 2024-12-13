<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt"    prefix="c" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/scripts/ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/styles/datepicker.css"></script>
<script language="javascript" type="text/javascript">
    function changeFormat(row){
        var amount = document.getElementById('amaun'+row);
        var x = parseFloat(amount.value);
        $('#amaun'+row).val(x.toFixed(2));
    }

    function changeFormat2(row){
    	var num = document.getElementById('amaun'+row).value;
    	num = num.toString().replace(/\$|\,/g,'');
    	if (isNaN(num))
	    	num = "0";
    	sign = (num == (num = Math.abs(num)));
    	num = Math.floor(num * 100+0.50000000001);
    	cents = num % 100;
    	num = Math.floor(num / 100).toString();
    	if(cents < 10)
    		cents = "0" + cents;
    	for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
    		num = num.substring(0, num.length - (4 * i + 3))+','+
    			num.substring(num.length-(4 * i + 3));
        $('#amaun'+row).val((((sign)?'':'-') + num + '.' + cents));
    }
</script>
<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
<p class=title>Langkah 4: Maklumat Tambahan Urusan</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah. Semua medan adalah mandatori.</span><br/>

<s:form action="/kaunter/permohonan" id="urusanInfoTamb">

    <fieldset class="aras1">
        <legend>Maklumat Tambahan Urusan</legend>

        <display:table name="${actionBean.senaraiUrusan}" id="row" class="tablecloth" >
            <display:column title="Urusan">
                <c:if test="${row != null && row.kodUrusan != null}">
                    ${row.kodUrusan} - ${row.namaUrusan}
                </c:if>
             </display:column>
            <display:column>
                <c:if test="${row.labelAmaun1 != null}">
                    ${row.labelAmaun1} :<s:text name="senaraiUrusan[${row_rowNum - 1}].amaun1" id="amaun${row_rowNum - 1}" 
                    size="12" style="text-align:right"
                     onblur="changeFormat2('${row_rowNum - 1}')" 
                    formatPattern="###,###,###.##"/>
                </c:if>
            </display:column>
            <display:column>
                <c:if test="${row.labelTarikh1 != null}">
                    ${row.labelTarikh1}:<s:text name="senaraiUrusan[${row_rowNum - 1}].tarikh1"
                        formatPattern="dd/MM/yyyy" class="datepicker" id="tarikh${row_rowNum - 1}" 
                        onblur="editDateBlur(this, 'DD/MM/YYYY')"
                                    onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                                    onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
                </c:if>
            </display:column>
            <display:column>
                <c:if test="${row.labelNilai1 != null}">
                    ${row.labelNilai1}:
                    <s:select name="senaraiUrusan[${row_rowNum - 1}].nilai1">
                        <s:options-collection collection="${actionBean.senaraiUrusan[row_rowNum - 1].nilai1Selection}"/>
                    </s:select>
                </c:if>
            </display:column>
        </display:table>

        <br/> 
        <p align="center">
            <s:submit name="Step3" value="Kembali" class="btn" />
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="test(this.form);"/>            
            <s:submit name="Step5" value="Seterusnya" class="btn" />
        </p>

    </fieldset>

</s:form>

<script type="text/javascript">
    $(document).ready(function(){
        $(".datepicker").datepicker($.datepicker.regional['ms']);
    });
</script>