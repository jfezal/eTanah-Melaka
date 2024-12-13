<%-- 
    Document   : bayaran_ansuran
    Created on : Jun 29, 2011, 4:08:48 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript" language="javascript" >
            $(document).ready(function() {

                for (var i = 1; i < 5; i++){
                    $("#field"+i).hide();
                    $('#bank'+i).attr("disabled", "true");
                    $('#caw'+i).attr("disabled", "true");
                    $('#rujukan'+i).attr("disabled", "true");
                    $('#amaun'+i).attr("disabled", "true");
                    $('#amaun'+i).val("0");
                }
            });
        </script>
        <script type="text/javascript">
            function change(row){
                var curr = document.getElementById('amaun' + row);
                var ax = document.getElementById('senaraiCaraBayaran'+row);
                for (var i = 0; i < (row+1); i++){
                    var a = document.getElementById('senaraiCaraBayaran'+i);
                    if(a.value != '0'){
                        $('#bank'+i).removeAttr("disabled");
                        $('#caw'+i).removeAttr("disabled");
                        $('#rujukan'+i).removeAttr("disabled");
                        $('#amaun'+i).removeAttr("disabled");
                        if (a.value == 'T'){
                            $('#field'+i).hide();
                            $('#bank'+i).hide();
                            $("#caw"+i).hide();
                            $("#rujukan"+i).hide();
                        }
                        else if((a.value == 'KW')||(a.value == 'WP')){
                            $('#bank'+i).val("PMB");
                            $('#bank'+i).attr("disabled", "true");
                            $('#bank'+i).show();
                            $('#caw'+i).show();
                            $('#rujukan'+i).show();
                            $('#field'+i).show();
                            if(a.value == '0'){
                                $('#a'+i).hide();
                            }
                        }
                        else{
                            $('#bank'+i).removeAttr("disabled");
                            $('#bank'+i).show();
                            $('#caw'+i).show();
                            $('#rujukan'+i).show();
                            $('#field'+i).show();
                            if(a.value == '0'){
                                $('#field'+i).hide();
                            }
                        }
                    }

                        for(var j = i+1; j < 5; j++){
                            var c = document.getElementById('senaraiCaraBayaran'+j);
                            if(c.value != '0'){
                                if((a.value == 'T')&&(c.value == 'T')){
                                    alert("Hanya satu mod Bayaran Tunai sahaja dibenarkan.");
                                    return c.value = '0';
                                }
                            }
                        }
                    }

                }

//                function sequencePayment2(row){
//                    if((row-1)>0){
//                        var x = document.getElementById('senaraiCaraBayaran'+(row-1));
//                        var y = document.getElementById('senaraiCaraBayaran'+(row));
//                        if((x.value == '0')&&(y.value != '0')){
//                            alert("Sila masukkan Mod Bayaran mengikut turutan.");
//                            return y.value = '0';
//                        }
//                    }
//                }
//
//                function autoBalance3(row, ax, curr){
//                    if(row != 0){
//                        if(ax.value != '0'){
//                            var t = document.getElementById('jumCaraBayar');
//                            var bal = (parseInt(t.value) + parseInt(curr.value)).toFixed(2);
//                            $("#jumCaraBayar").val(bal);
//                        }
//                    }
//                }

                function updateTotal (inputTxt,row){
                    var total = 0;
            <%--for (var i = 0; i <bil; i++){--%>
                    var a = document.getElementById('amaun' + row)
                    if ((isNaN(a.value))||((a.value) =="")){
                        alert("Nombor tidak sah");
                        inputTxt.value = RemoveNonNumeric(a);
                        $('#jumCaraBayar').val("0.00");
                        return;
                    }
                    total += parseFloat(a.value);
            <%--}--%>
                    var t = document.getElementById('jumCaraBayar');
                    t.value = total.toFixed(2);
                    inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
                    updtTot();
                  
                }

                function updtTot(){
                    var total = 0;
                    for (var i=0; i<5; i++){
                        var a = document.getElementById('amaun' + i)
                        total += parseFloat(a.value);
                    }
                    var t = document.getElementById('jumCaraBayar');
                    t.value = total.toFixed(2);
                }

                function validate(){
                    var u = parseFloat($('#total').val());
                    var t = parseFloat($('#jumCaraBayar').val());
                    if(u > t){
                        var bal = u -t;
                        alert("Bayaran anda kurang RM "+(bal).toFixed(2));
                        return false;
                    }
                    for (var i = 0; i < 5; i++){
                        var a = document.getElementById('senaraiCaraBayaran'+i);
                        var c = $('#rujukan'+i).val();
                        if((a.value != '0')&&(a.value != 'T')){
                            if(c == ""){
                                alert("Sila Masukkan Nombor Rujukan.");
                                $('#rujukan'+i).focus();
                                return false;
                            }
                        }
                    }
                    return true;
                }
        </script>

<s:form beanclass="etanah.view.stripes.pelupusan.RayuanAnsuranActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Rayuan Ansuran Bayaran</legend><br/>
             <p>
                    <label>Jumlah Amaun Sebenar (RM):</label>

                   ${actionBean.permohonanTuntutanKos.amaunSebenar} 
             </p>
             <p>
                    <label>Jumlah Amaun Yang Perlu Dibayar (RM):</label>

                   ${actionBean.permohonanTuntutanKos.amaunTuntutan} 
             </p>

            <p>
                    <label>Bilangan Bulan Untuk Bayar Secara Ansuran:</label>

                   ${actionBean.bilBulan} 
             </p>
             <br/>
             
            
            <div class="content" align="center">
               <table class="tablecloth">
                   <tr><th></th><th>Bayaran</th><th>Tarikh Akhir Bayaran</th></tr>
                  <c:if test="${actionBean.bulan1 ne null}">
                  <tr>
                      <th>1. </th><td>RM ${actionBean.bulan1}</td><td><s:format value="${actionBean.tarikhAkhirBayaran1}" formatPattern="dd/MM/yyyy"/></td>
                  </tr>
                  </c:if>
                  <c:if test="${actionBean.bulan2 ne null}">
                  <tr><th>2. </th><td>RM ${actionBean.bulan2}</td><td><s:format value="${actionBean.tarikhAkhirBayaran2}" formatPattern="dd/MM/yyyy"/></td>
                  </tr>
                  </c:if>
                  <c:if test="${actionBean.bulan3 ne null}">
                  <tr><th>3. </th><td>RM ${actionBean.bulan3}</td><td><s:format value="${actionBean.tarikhAkhirBayaran3}" formatPattern="dd/MM/yyyy"/></td>
                  </tr>
                  </c:if>
                  <c:if test="${actionBean.bulan4 ne null}">
                  <tr><th>4. </th><td>RM ${actionBean.bulan4}</td><td><s:format value="${actionBean.tarikhAkhirBayaran4}" formatPattern="dd/MM/yyyy"/></td>
                   </tr>
                  </c:if>
                  <c:if test="${actionBean.bulan5 ne null}">
                  <tr><th>5. </th><td>RM ${actionBean.bulan5}</td><td><s:format value="${actionBean.tarikhAkhirBayaran5}" formatPattern="dd/MM/yyyy"/></td>
                  </tr>
                  </c:if>
                  <c:if test="${actionBean.bulan6 ne null}">
                  <tr><th>6. </th><td>RM ${actionBean.bulan6}</td><td><s:format value="${actionBean.tarikhAkhirBayaran6}" formatPattern="dd/MM/yyyy"/></td>
                   </tr>
                  </c:if>
               </table>

           </div>
           <%--  <p align="center">
             Amaun untuk di bayar :
             <s:text name="amaun1"/>
             </p> --%>
             
         
            <p class=title>Cara Bayaran</p>
                <fieldset class="aras1">
                    <display:table name="${actionBean.senaraiCaraBayaran}" id="row" style="width:100%;" class="tablecloth">

                        <display:column title="Bil">
                            ${row_rowNum}
                        </display:column>

                        <display:column title="Cara Bayaran" >
                            <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].kodCaraBayaran.kod"
                                            id="senaraiCaraBayaran${row_rowNum - 1}" onchange="javaScript:change(${row_rowNum -1})">
                                <s:option value="0" label="Pilih ..." />
                                <s:options-collection collection="${listUtil.senaraiKodCaraBayaran}"  label="nama" value="kod" sort="nama"/>
                            </s:select>
                        </display:column>

                        <display:column title="Bank / Agensi Pembayaran" >
                            <s:select name="senaraiCaraBayaran[${row_rowNum - 1}].bank.kod" id="bank${row_rowNum - 1}">
                                <s:option label="Pilih..." value="0" />
                                <s:options-collection collection="${listUtil.senaraiKodBank}"  label="nama" value="kod" />
                            </s:select>
                        </display:column>

                        <display:column title="Cawangan" >
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].bankCawangan" size="20" id="caw${row_rowNum - 1}"/>
                        </display:column>

                        <display:column title="No. Rujukan" >
                            <em id="field${row_rowNum - 1}">*</em><s:text name="senaraiCaraBayaran[${row_rowNum - 1}].noRujukan" size="20" id="rujukan${row_rowNum - 1}"/>
                        </display:column>

                        <display:column title="Amaun (RM)" >
                            <s:text name="senaraiCaraBayaran[${row_rowNum - 1}].amaun" size="12" class="number"
                                          onblur="javascript:updateTotal(this,${row_rowNum - 1});" id="amaun${row_rowNum - 1}" />
                        </display:column>

                        <display:footer>
                            <tr>
                                <td colspan="5" align="right">Jumlah (RM):</td>
                                <td><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="12"
                                           class="number" readonly="readonly" style="background:transparent;border:0px;" /></td>
                            <tr>
                            </display:footer>

                        </display:table >
                </fieldset>
                  <p align="center">
                <s:button name="simpanBayaran" id="save" value="Bayar" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
           </p> 
           
        </fieldset>
    </div>
 </s:form>
   

