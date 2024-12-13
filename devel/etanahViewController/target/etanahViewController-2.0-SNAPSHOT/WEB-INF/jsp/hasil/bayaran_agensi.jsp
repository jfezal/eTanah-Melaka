<%-- 
    Document   : bayaran_agensi
    Created on : Feb 11, 2011, 11:05:35 AM
    Author     : abdul.hakim
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
    });
    function dateValidation(value, id){
        <%--alert(id);--%>
        if(id == 'from'){
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var today = new Date();
            var sdate = new Date(fulldate);
            if (sdate > today){
                alert("Tarikh yang dimasukkan tidak sesuai.");
                $('#'+id).val("");
            }
        }
        if(id == 'to'){
            var f = document.getElementById('to');
            if(f.value != ""){
                var vsplitFrom = f.value.split('/');
                var fulldateFrom = vsplitFrom[1]+'/'+vsplitFrom[0]+'/'+vsplitFrom[2];
                <%--alert(fulldateFrom);--%>
                var sdateFrom = new Date(fulldateFrom);

                if (sdateFrom > sdate){
                    alert("Tarikh Hingga yang dimasukkan tidak sesuai.");
                    $('#'+id).val("");
                }
            }
        }
    }

    function search(){
        var f = document.getElementById('from');
        var t = document.getElementById('to');
        if(f.value == ''){
            alert("Sila masukkan tarikh dari.");
            $('#from').focus()
            return false;
        }else if(t.value == ''){
            alert("Sila masukkan tarikh hingga.");
            $('#to').focus()
            return false;
        }else{
            return true;
        }
    }
</script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Bayaran Agensi</font>
                </div></td></tr>
    </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:errors />
<stripes:form beanclass="etanah.view.stripes.hasil.BayaranAgensiActionBean" id="bayar_agensi">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label><em>*</em>Pilih Agensi :</label>
                <stripes:select name="agensi" id="kodPBT">
                    <stripes:option label="Pilih Agensi ..." value="" />
                    <c:forEach items="${actionBean.senaraiKodPBT}" var="i" >
                        <stripes:option value="${i.kod}">${i.kod} - ${i.nama}</stripes:option>
                    </c:forEach>
                </stripes:select>
            </p>

            <p>
                <label><em>*</em>Tarikh Dari :</label>
                <stripes:text name="tarikhDari" class="datepicker" readonly="true" onchange="dateValidation(this.value, 'from')" id="from"/>
            </p>

            <p>
                <label><em>*</em>Tarikh Hingga :</label>
                <stripes:text name="tarikhHingga" class="datepicker" readonly="true" onchange="dateValidation(this.value,'to')" id="to"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="Step2" value="Cari" class="btn" onclick="return search()"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('bayar_agensi');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div align="center">
                    <table class="tablecloth">
                        <tr>
                            <th>Bil.</th>
                            <th>Kod Transaksi</th>
                            <th width="500">Nama</th>
                            <th>Amaun (RM)</th>
                        </tr>
                        <tr>
                            <td>1.</td>
                            <td>61401</td>
                            <td>Cukai Tanah Semasa</td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.cukaiTanah}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>2.</td>
                            <td>61402</td>
                            <td>Cukai Tanah Tunggakan</td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.tunggakanCukaiTanah}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>3.</td>
                            <td>76152</td>
                            <td>Notis dan Denda Lewat Bayaran Cukai Tanah</td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.dendaLewat}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td>4.</td>
                            <td>99011</td>
                            <td>Notis 6A</td>
                            <td><div align="right"><fmt:formatNumber value="${actionBean.notis}" pattern="#,##0.00"/></div></td>
                        </tr>
                        <tr>
                            <td colspan="3" style="background-color:99CCFF"><div align="right"><b>Jumlah (RM) : </b></div></td>
                            <td style="background-color:99CCFF"><div align="right"><fmt:formatNumber value="${actionBean.jumlah}" pattern="#,##0.00"/></div></td>
                        </tr>
                    </table>
                </div>
                <br>
            </fieldset>
        </div>
        <div align="center"><table style="width:99.2%" bgcolor="green">
            <tr>
                <td align="right">
                    <stripes:submit name="Step3" value="Seterusnya" class="btn"/>
                </td>
            </tr>
        </table></div>
    </c:if>
</stripes:form>