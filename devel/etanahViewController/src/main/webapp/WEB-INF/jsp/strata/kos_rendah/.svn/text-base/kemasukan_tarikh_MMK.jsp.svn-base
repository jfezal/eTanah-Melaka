<%-- 
    Document   : kemasukan_tarikh_MMK
    Created on : Jul 21, 2011, 1:39:27 PM
    Author     : zadhirul.farihim
--%>

<%-- 
    Document   : kertas_mmk3
    Created on : Jun 14, 2011, 10:16:35 PM
    Author     : zadhirul.farihim
--%>


<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE html>
<script type="text/javascript">

    function validate(){
        var tarikh = document.getElementById("tarikhMesyuarat").value;
        if(tarikh==""){
            alert("Sila Masukkan Tarikh.");
            document.getElementById("tarikhMesyuarat").focus();
            return false;
        }
        return true;
    }
    
    function simpanData(event, f){
        if(validate()){
            
            if(confirm('Adakah anda pasti untuk simpan data ini?')){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div').html(data);

                },'html');
            }
        }

    }
 

</script>

<s:form name="form1" beanclass="etanah.view.strata.KertasMMKActionBean">
    <s:messages/>
    <s:errors/> 
    <font color="blue" size="2"><b>Sila isi dan simpan semua maklumat bertanda </font>( <font color="red">*</font> ) <font color="blue" size="2">sebelum menekan butang Selesai.</font></b></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Tajuk</legend>
            <div class="content" align="left">
                <table border="0">
                    <tr><td colspan="4" align="center"><b>( MAJLIS MESYUARAT KERAJAAN )</b></td></tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr><td colspan="4" align="center"><b>Tarikh Mesyuarat : </b><font color="red">*</font><s:text name="tarikhMesyuarat" id="tarikhMesyuarat" class="datepicker" size="20" /> 
                            <s:button name="simpanTarikhMesyuarat" value="Simpan" class="btn" onclick="if(validate()){doSubmit(this.form,this.name,'page_div')};"/></td></tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center"></td>
                    </tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr><td width="100px"><strong>&nbsp;</strong></td>
                        <td colspan="2"><b><font style="text-transform: uppercase"><center>${actionBean.tajuk}</center></font></b></td>
                        <td width="100px"><strong>&nbsp;</strong></td>
                    </tr>
                    <tr>
                        <td colspan="4"><strong>&nbsp;</strong></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>1. Tujuan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable1" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="1.${bil+1}"/></td>
                                        <td>

                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="tujua${i}" id="tujua${i}"  rows="5" cols="120" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>
                                        <td>        
                                        </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount1" value="${i-1}" id="rowCount1"/>
                            </table></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>2. Latar Belakang</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable2" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan2}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="2.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="latarblkg${i}" id="latarblkg${i}"  rows="5" cols="120" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount2" value="${i-1}" id="rowCount2"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>3. Asas Permohonan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable3" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />

                                <c:forEach items="${actionBean.senaraiLaporanKandungan3}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="3.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="asaspermohonan${i}" id="asaspermohon${i}"  rows="5" cols="120">${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>

                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount3" value="${i-1}" id="rowCount3"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>4. Ulasan Pengarah Tanah Dan Galian</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable4" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan4}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="4.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="ulasanpengarah${i}" id="ulasanpengarah${i}"  rows="5" cols="120">${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>
                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount4" value="${i-1}" id="rowCount4"/>
                            </table></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>5. Syor Pengarah Tanah Dan Galian</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable5" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan5}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="5.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="syorpngarah${i}" id="syorpngarah${i}"  rows="5" cols="120">${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>

                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount5" value="${i-1}" id="rowCount5"/>
                            </table></td>
                    </tr>
                </table>
            </div>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>6. Keputusan</legend>
            <div class="content" align="left">
                <table border="0">

                    <tr valign="top">
                        <td height="23">&nbsp;</td>
                        <td colspan="3">

                            <table id ="dataTable6" border="0">

                                <c:set var="i" value="1" />
                                <c:set var="bil" value="0" />
                                <c:forEach items="${actionBean.senaraiLaporanKandungan6}" var="line">
                                    <tr style="font-weight:bold">
                                        <td style="display:none">${line.idKandungan}</td>
                                        <td><c:out value="6.${bil+1}"/></td>
                                        <td>
                                            <font style="text-transform: uppercase">
                                            <s:textarea class="normal_text" readonly="readonly" name="kputusan${i}" id="kputusan${i}"  rows="5" cols="120" >${line.kandungan}</s:textarea>
                                            </font>

                                        </td>

                                        <td>

                                        </td>

                                    </tr>

                                    <c:set var="i" value="${i+1}" />
                                    <c:set var="bil" value="${bil+1}" />
                                </c:forEach>
                                <s:hidden name="rowCount6" value="${i-1}" id="rowCount6"/>
                            </table></td>
                    </tr>

                </table>
            </div>
        </fieldset>
    </div>

</s:form>

