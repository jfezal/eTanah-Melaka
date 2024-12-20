<%--
	Document : maklumat_bayaran_ansuran
	Author   : Mohd Hairudin Mansur
	Version  : 1.0 14 Dec 2009
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script  language="javascript" >
     $(document).ready(function() {
        var rep = ${actionBean.denda};
        if(rep == true){
            alert("Perhatian :\nAnsuran yang melebihi bulan Mei akan dikira termasuk denda lewat.");
        }else{$('#nota').hide();
        }
     });
    
function updateTotal(elmnt,inputTxt){
    var a = document.getElementById('amaun')

    if (isNaN(a.value)){
        elmnt.value = RemoveNonNumeric(inputTxt);
        return;
    }
    if(a.value > 6){
        alert("Bayaran Ansuran tidak boleh melebihi 6 bulan.");
        $('#generate').hide();
        return elmnt.value = "";
    }
    if(((a.value)=="")||((a.value)==0)){
        alert("Sila masukkan tempoh ansuran");
        $('#generate').hide();
        return;
    }
    if((a.value)==1){
        alert("Tempoh Bayaran Ansuran hendaklah melebihi daripada 1 bulan.");
        $('#generate').hide();
        alert('s');
        return;
    }else{
        $('#generate').show();
    }
}

function val(){
    var temp = document.getElementById('amaun');
    var sbb = document.getElementById('sbb');
    var trkh = document.getElementById('dt');
    if((temp.value != 0)&&(sbb.value != "")&&(trkh.value != "")){
//        $('#generate').show();
    }
    <%--else if(sbb.value == ""){
        alert("Sila Masukkan Alasan");
        $('#sbb').focus();
        return false;
    }else if(trkh.value == ""){
        alert("Sila Masukkan Tarikh Mula Ansuran");
        $('#dt').focus();
       return false;
    }--%>
}

function RemoveNonNumeric( strString )
{
      var strValidCharacters = "123456789.";
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

</script>
<s:form beanclass="etanah.view.stripes.hasil.MaklumatBayaranAnsuranActionBean">
<s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Bayaran Ansuran</legend>
            <p>
                <label><em>*</em>Ansuran Sebanyak :</label>
                <s:text name="tempoh" onkeyup="javascript:updateTotal(this, this.value);" onblur="val()" id="amaun" size="7" maxlength="2"/> bulan.
                <br>
                <label>&nbsp;</label>
                <sub><font color="red">Ansuran hanya boleh dibuat tidak melebihi 6 bulan.</font></sub>
            </p>

            <p>
                <label><em>*</em>Alasan :</label>
                <s:textarea name="permohonan.sebab" cols="40" rows="5" id="sbb" onblur="val()"/>
            </p>

            <p>
                <label><em>*</em>Tarikh Mula Ansuran :</label>
                <s:text name="tarikhAnsuran" class="datepicker" id="dt" onchange="val()"/>
            </p>
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" id="generate" onclick="doSubmit(this.form, this.name, 'page_div');" name="janaJadual" value="Jana Jadual"/>
            </p>
        </fieldset>
    </div>
    <p></p>

    <div class="subtitle">
        <c:if test="${actionBean.flag}">
            <fieldset class="aras1">
                <legend>Jadual Pembayaran</legend>
                <%--<p>
                    <label>Nombor Akaun :</label>
                    ${actionBean.akaunAmanah.noAkaun}&nbsp;
                </p>--%>
                <div align="center">
                    <table border=0 align="center" class="tablecloth">
                        <tr>
                            <th>Bulan</th>
                            <c:forEach var="i" begin="1" end="${actionBean.tempoh}" >
                                <th  align="center">
                                    <%--<s:text style="text-align:center" name="jadual[${i - 1}]" size="10"/>--%>
                                    ${actionBean.jadual[i-1]}<br>
                                    ${actionBean.year[i-1]}
                                </th>
                            </c:forEach>
                                <th align="center">
                                    Jumlah
                                </th>
                        </tr>

                        <tr>
                            <th>Bayaran (RM)</th>
                            <%--<td align="center">
                                <div align="center"><fmt:formatNumber value="${actionBean.firstPayment}" pattern="#0.00"/></div>
                            </td>--%>
                            <c:forEach var="i" begin="0" end="${(actionBean.tempoh)-2}" >
                                <td  align="center">
                                    <div align="center">
                                        
                                        <fmt:formatNumber value="${actionBean.monthly}" pattern="#0.00"/></div>
                                    <%--&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.jadual[i-1]}&nbsp;&nbsp;&nbsp;&nbsp;--%>
                                </td>
                            </c:forEach>
                            <td align="center">
                                <div align="center"><fmt:formatNumber value="${actionBean.lastPayment}" pattern="#0.00" /></div>
                            </td>
                            <td align="center">
                                <div align="center"><fmt:formatNumber value="${actionBean.totalValue}" pattern="#0.00" /></div>
                            </td>
                        </tr>
                    </table>
                    <div id="nota"><sub><font color="red">**Amaun yang dicadangkan dalam Jadual Pembayaran adalah termasuk Denda Lewat tahun semasa.</font></sub></div>
                    
                </div>
                            <br>
                            <br>
                            <p>
                <label>No Resit :</label>
              ${actionBean.noResitDepo}
            </p>
            <label>Amaun :</label>
               RM <fmt:formatNumber value="${actionBean.amauntAnsuran}" pattern="#0.00" /><p>
                <label>Denda :</label>
              RM <fmt:formatNumber value="${actionBean.fine}" pattern="#0.00" /> 
            </p>
                <div align="center">
                    <s:button class="btn" id="simpan" onclick="doSubmit(this.form, this.name, 'page_div');" disabled="${actionBean.simpanBtn}" name="generateTable" value="Simpan"/>
                </div>
            </fieldset>
        </c:if>
    </div>
</s:form>
