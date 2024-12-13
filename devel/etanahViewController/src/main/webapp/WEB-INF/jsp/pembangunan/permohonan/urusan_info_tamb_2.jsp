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
    $(document).ready(function() {
        $('#Step5').attr("disabled", "disabled");
        $('input[name="bilMohon"]').click(function() {
            $('input[name="Step5"]').removeAttr('disabled');
        });
        //        var htsps =  document.getElementById('htsps');
        //        var htspb = document.getElementById('htspb');
        //        if(htsps.checked){
        //            $('#Step5').removeAttr('disabled');
        //        }else if(htspb.checked){
        //            $('#Step5').removeAttr('disabled');
        //        }else{
        //            $('#Step5').attr("disabled", "disabled"); 
        //        }
        var m = document.getElementById('amtDummy');
        var label = document.getElementById('lbl');
        if (m.value == '') {
            $('#amaun1').val('0.00');
            //            $('#amaun1').show();
            //            $('#amaun2').hide();
        }
        if (label.value == 'Jumlah Petak' && m.value == '') {
            //            $('#amaun1').hide();
            //            $('#amaun2').show();           
            $('#amaun2').val('0');
            $('#amaun3').val('0');
            $('#amaun4').val('0');
        }
        var ursn = document.getElementById("ursn");
        if (ursn.value == 'Melepaskan Gadaian') {
            $("#m").hide();
        }
    });
    
    
</script>
<script language="javascript" type="text/javascript">
    function changeFormat(row) {
        var amount = document.getElementById('amaun' + row);
        var x = parseFloat(amount.value);
        $('#amaun' + row).val(x.toFixed(2));
    }

    function changeFormat2(row) {
        var num = document.getElementById('amaun' + row).value;
        num = num.toString().replace(/\$|\,/g, '');
        if (isNaN(num))
            num = "0";
        sign = (num == (num = Math.abs(num)));
        num = Math.floor(num * 100 + 0.50000000001);
        cents = num % 100;
        num = Math.floor(num / 100).toString();
        if (cents < 10)
            cents = "0" + cents;
        for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
            num = num.substring(0, num.length - (4 * i + 3)) + ',' +
            num.substring(num.length - (4 * i + 3));
        $('#amaun' + row).val((((sign) ? '' : '-') + num + '.' + cents));
    }
    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

        return true;
    }

    function clearForm() {
        $('#amaun1').val('0');
        $('#amaun2').val('0');
        $('#amaun3').val('0');
        $('#amaun4').val('0');
        $("#htsps").value = false;
        $("#htspb").value = false;
    }
    function validate(){
        var htsps = document.getElementById("htsps");
        var htspb = document.getElementById("htspb");
        if (htsps.checked || htspb.checked)
        {
            return true;
        }else{
            alert('Sila Buat Pilihan Dahulu.');
            document.getElementById("htspb").focus();
            return false;
                
        }

    }

</script>

<style type="text/css">
    .labelspoc1 {
        width: 20em;
        float: left;
        text-align: right;
        margin-right: 0px;
        display: block;
        /*color: #666666;*/
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        /*font-family: Geneva, Arial, Helvetica, sans-serif;*/
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<p class=title>URUSAN: ${actionBean.senaraiUrusanLabel}</p>
<p class=title>Langkah 4: Maklumat Tambahan Urusan</p>

<s:messages />
<s:errors />

<span class=instr>Masukkan maklumat yang diperlukan di bawah. Semua medan adalah mandatori.</span><br/>

<s:form action="/pembangunan/permohonan" name="form1" id="urusanInfoTamb">

    <fieldset class="aras1">
        <legend>Maklumat Tambahan Urusan</legend>

        <s:text name="senaraiUrusanLabel"  value="${actionBean.senaraiUrusanLabel}" id="ursn" style="display:none;" />
        <s:text name=" "  value="${actionBean.urusan.labelAmaun1}" id="lbl" style="display:none;"/>

        <%--Condition added by murali(STRATA) to differentiate with urusan:PBS and PBTS 22/08/2012: START--%>
        <c:if test="${actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Bangunan Sementara'
                      && actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara'}">
            <c:if test="${actionBean.urusan.labelAmaun1 != null}">
                <p><label for="amaun1" class="labelspoc">
                        <c:if test="${actionBean.kodNegeri ne 'n9' && actionBean.urusan.kodUrusan ne 'PMTM'}">                       
                            <em>*</em>
                        </c:if>${actionBean.urusan.labelAmaun1}: 
                    </label>
                        <c:choose>
                            <c:when test="${actionBean.urusan.labelAmaun1 eq 'Jumlah Petak'}">
                                <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
                            </c:when>
                             <c:when test="${actionBean.urusan.labelAmaun1 eq 'Luas (Hektar)'}">
                                 <s:text name="urusan.amaun1" id="amaun1"
                                size="12" style="text-align:right"
                                onblur=""
                                formatPattern="###,###,###.0000"/>
                            </c:when>
                            <c:otherwise>
                                 <s:text name="urusan.amaun1" id="amaun1"
                                size="12" style="text-align:right"
                                onblur="changeFormat2('1')"
                                formatPattern="###,###,###.00"/>
                            </c:otherwise>
                        </c:choose>
                    <%--<c:if test="${actionBean.urusan.labelAmaun1 eq 'Jumlah Petak'}">
                        <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
                    </c:if>
                    
                    <c:if test="${actionBean.urusan.labelAmaun1 ne 'Jumlah Petak'}">
                        <s:text name="urusan.amaun1" id="amaun1"
                                size="12" style="text-align:right"
                                onblur="changeFormat2('1')"
                                formatPattern="###,###,###.0000"/></c:if>--%>
                    </p>
            </c:if>
        </c:if>

        <%--added by murali(STRATA) to differentiate with urusan:PBS and PBTS 22/08/2012: START--%>
        <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan Sementara'}">
            <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Blok Sementara:</label>
                <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
            </p>

            <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Kekal:</label>
                <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
            </p>
        </c:if>
        <%--End--%>

        <%--added by Syafiq Az(STRATA) to differentiate with urusan:PHPC 29/05/2013: START--%>
        <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Petak'}">
            <c:if test="${actionBean.disRadio eq true}">
                <p><s:radio name="bilMohon" id="htsps"  value="1" disabled="disabled"> </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Sempadan
                </p>
            </c:if>
            <c:if test="${actionBean.disRadio eq false}">
                <p><s:radio name="bilMohon" id="htsps"  value="1" > </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Sempadan
                </p>
            </c:if>
            <p><s:radio name="bilMohon" id="htspb"  value="2"> </s:radio>&nbsp; Hakmilik Strata Sambungan - Pecah Bahagian
            </p>
        </c:if>
        <%--End--%>

        <%--added by murali(STRATA) to differentiate with urusan:PBTS 02/10/2012: START--%>
        <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Bangunan atau Bangunan dan Tanah bagi Bangunan Sementara'}">
            <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Blok Sementara:</label>
                <s:text name="urusan.amaun1" id="amaun2" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
            </p>

            <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Blok Kekal:</label>
                <s:text name="urusan.amaun2" id="amaun3" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
            </p>

            <p><label for="amaun1"  class="labelspoc1"><em>*</em>Jumlah Petak Tanah:</label>
                <s:text name="urusan.amaun3" id="amaun4" size="12" style="text-align:right" onkeypress="return isNumberKey(event)"/>
            </p>
        </c:if>
        <%--End--%>

        <s:text name="urusan.amaun1" id="amtDummy" style="display:none;"/>

        <c:if test="${actionBean.urusan.labelTarikh1 != null}">
            <p><label for="tarikh1" class="labelspoc">
                    <em id="m">*</em>${actionBean.urusan.labelTarikh1} :</label>
                    <s:text name="urusan.tarikh1"
                            formatPattern="dd/MM/yyyy" class="datepicker2" id="tarikh1" readonly="true"
                            onblur="editDateBlur(this, 'DD/MM/YYYY')"
                            onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                            onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/>
            </p>
        </c:if>

        <c:if test="${actionBean.urusan.labelNilai1 != null}">
            <p><label for="nilai1" class="labelspoc">
                    <em>*</em>${actionBean.urusan.labelNilai1} :</label>
                    <s:select name="urusan.nilai1" id="nilai1">
                        <%--<s:options-collection collection="${actionBean.urusan.nilai1Selection}"/>--%>
                        <c:forEach items="${actionBean.urusan.nilai1Selection}" var="line">
                            <c:choose>
                                <c:when test="${line eq 'TSSKB'}">
                                    <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Bangunan </s:option>
                            </c:when>
                            <c:when test="${line eq 'TSSKP'}">
                                <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Pertanian </s:option>
                            </c:when>
                            <c:when test="${line eq 'TSSKI'}">
                                <s:option value="${line}">${line} - Tukar Syarat Sama Kategori Industri </s:option>
                            </c:when>
                            <c:when test="${line eq 'TSPSN'}">
                                <s:option value="${line}">${line} - Tukar Syarat Pindaan Syarat Nyata </s:option>
                            </c:when>
                            <c:when test="${line eq 'TSLK'}">
                                <s:option value="${line}">${line} - Tukar Syarat Lain Kategori </s:option>
                            </c:when>
                            <c:otherwise>
                                <s:option value="${line}">${line} </s:option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </s:select>
            </p>
        </c:if>

        <c:if test="${actionBean.urusan.labelTeks1 != null}">
            <p><label for="nilai1" class="labelspoc">
                    <em>*</em>${actionBean.urusan.labelTeks1} :</label>
                    <s:text name="urusan.teks1" id="teks1" size="50"/>
            </p>
        </c:if>

        <br/>
        <p align="center">
            <s:submit name="Step3" value="Kembali" class="btn" />
            <s:submit name="CancelAll" value="Batal" class="btn" />
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
            <c:if test="${actionBean.senaraiUrusanLabel ne 'Permohonan Pecah Bahagi Petak'}">
                <s:submit name="Step5" value="Seterusnya" class="btn" />
            </c:if>
            <c:if test="${actionBean.senaraiUrusanLabel eq 'Permohonan Pecah Bahagi Petak'}">
                <s:submit name="Step5" id="Step5"  value="Seterusnya" class="btn" onclick="return validate()" />
            </c:if>

        </p>

    </fieldset>

</s:form>

<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker2").datepicker(
        {
            dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true,
            maxDate: new Date()
        });
    });
</script>