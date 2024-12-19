<%-- 
    Document   : borang4deView
    Created on : Oct 12, 2015, 12:02:55 PM
    Author     : Hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">
    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function RemoveNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
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
       
    function showReport(id,reportName){
        var noPermit = '${actionBean.noPermit}';
        var url = '${pageContext.request.contextPath}/reportGeneratorServlet?reportName='+reportName+'&report_p_id_mohon='+id+'&report_p_no_permit='+noPermit;  
        window.open(url, "eTanah","status=0,toolbar=0,location=no,menubar=0,width=900px,height=700px,scrollbars=yes");
    }            
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
<stripes:form beanclass="etanah.view.stripes.permit.PermitActionBean">
    <s:messages/>
    <s:hidden name="noFail" id="noFail" />   
    <s:hidden name="noPermit" id="noPermit" />

    <div class="subtitle">
        <fieldset class="aras1">

            <legend>
                <td align="center"><font color="#001848">Kanun Tanah Negara</font></td>
            </legend>
            <br/>
            <div align="center"><font color="#001848"><b> Borang 4De</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b> (Jadual Keenam Belas)</b></font></div>
            <br/>

            <div align ="center">
                <div align="center"><font color="#001848"><b>PERMIT BAGI PENGGUNAAN RUANG UDARA ATAS</b></font></div> 

                <td>
                    <c:if test = "${actionBean.radio1 eq 'K'}">
                        <font color="#001848"> TANAH KERAJAAAN &nbsp;</font>
                    </c:if>
                    <c:if test = "${actionBean.radio1 eq 'R'}">
                        <font color="#001848"> TANAH RIZAB &nbsp;</font>
                    </c:if>                                
                </td>             
            </div>
            <br/>       

            <div class="content" align="center">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Fail</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.noFail}</td>
                    </tr>                   
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Permit</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.kodPermit}  ${actionBean.noPermit}</td>                        
                    </tr>   
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Daerah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.daerahNama}</td>   
                    </tr>
                </table>
            </div>

            <div class="content" align="center" id="bayar">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Resit</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.noResit}</td>  
                    </tr>
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Fi (RM)</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><fmt:formatNumber pattern="#,##0.00" value="${actionBean.bayaran}"/></td>  
                    </tr> 
                </table>
            </div>

            <div class="content" align="center">
                <div id ="pihak">
                    <table border="0" width="50%">
                        <tr>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nama Pemegang Permit</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td>${actionBean.nama}</td> 
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Alamat</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td>${actionBean.alamat1}</td>                 
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                      
                            <td>${actionBean.alamat2}</td>                                      
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                       
                            <td>${actionBean.alamat3}</td>                                               
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;"></font> </td>                     
                            <td>${actionBean.alamat4}</td>                            
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Poskod</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>                           
                            <td>${actionBean.poskod}</td>                           
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Negeri</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font></td>      
                            <td>${actionBean.negeriNama}</td>
                        </tr>
                        <tr>
                            <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. KP</font></td>
                            <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                            <td>${actionBean.noPengenalan}</td>  
                        </tr>  
                    </table>
                </div>
            </div>

            <br></br>
            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    adalah dengan ini, diberi kebenaran untuk  menggunakan ruang udara atas tanah  yang diperihalkan di bawah
                    ini bagi maksud mendirikan, menyenggara dan menduduki *struktur-struktur/sebagaimana
                    yang diluluskan oleh Pihak Berkuasa Negeri, tertakluk kepada syarat-syarat yang dinyatakan di
                    bawah dan kepada peruntukan yang ditetapkan oleh Kaedah-Kaedah.
                </font>
                </tr> 
            </div>
            <br/>

            <div class="content" align="center">
                <table border="0" width="50%">                    
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Dikeluarkan Pada </font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhPermit}"/></td>  
                    </tr> 
                </table>
            </div>
            <br/>         
    </div>
</fieldset>
</div>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> PERIHAL MENGENAI TANAH </b></font></div>
        </legend>
        <div class="content" align="center">
            <table border="0" width="35%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Bandar/Pekan/Mukim</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.bandarNama}</td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanah Kerajaan/Tanah Rizab</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.tanahNama}</td> 
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No Lot/PT</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.kodLotNama} ${actionBean.noLot}</td>  
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tempat</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.tempat}</td>  
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Luas Tanah Untuk Diduduki</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.luas} &nbsp;
                        <c:if test = "${actionBean.radio2 eq 'H'}">
                            Hektar
                        </c:if>
                        <c:if test = "${actionBean.radio2 eq 'M'}">
                            Meter Persegi
                        </c:if> 
                        <c:if test = "${actionBean.radio2 eq 'MP'}">
                            Meter Padu
                        </c:if> 
                    </td>                         
                </tr>
            </table>          
            <br/>

            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    (Pelan kasar/Pelan tanah, untuk maksud pengenalan, dikeluarkan secara berasingan di dalam Borang P2e)
                </font>
                </tr> 
            </div>           
    </fieldset>
</div>

<br/>               
<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> SYARAT-SYARAT </b></font></div>
        </legend>
        <div class="content" align="left">
            <table border="0" width="85%">
                <tr>
                    <td width="100%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (1) Permit ini akan bermula pada <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhMula}"/>
                            dan tamat tempoh pada <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhtamat}"/>.
                        </font>
                    </td>
                </tr>
            </table>

            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (2) Ruang udara atas tanah ${actionBean.syaratTanah} hendaklah tidak digunakan bagi apa-apa maksud selain dari mendirikan,
                            penyenggaraan dan pendudukan *struktur/ struktur-struktur yang diperihalkan di bawah ini yang kepadanya permit itu dikeluarkan :-
                        </font>
                    </td>                                                                                         
                </tr>
            </table>
            <br/>
            <table border="0" width="50%">  
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Jenis Struktur</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.jenisStruktur}</td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Lokasi</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.lokasi}</td>
                </tr>
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Kawasan Ruang Udara Yang Terbabit (volume)</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.luas} &nbsp;
                        <c:if test = "${actionBean.jenisLuas eq 'H'}">
                            Hektar
                        </c:if>
                        <c:if test = "${actionBean.jenisLuas eq 'M'}">
                            Meter Persegi
                        </c:if> 
                        <c:if test = "${actionBean.jenisLuas eq 'MP'}">
                            Meter Padu
                        </c:if> 
                    </td>  
                </tr>
            </table>

            <br/>
            <table border="0" width="35%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (3) Permit ini boleh dibatalkan di bawah seksyen 75G. 
                        </font>
                    </td>                                                                                         
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (4) Permit ini tidak boleh diserahak,kecuali dengan kelulusan Pihak Berkuasa Negeri terlebih dahulu. 
                        </font>
                    </td>                               
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;${actionBean.tambah}
                        </font>                       
                    </td>        

                </tr>
            </table>

            <br/>         
        </div>
    </fieldset>
</div>

<c:if test = "${actionBean.pengguna.perananUtama.kumpBPEL eq 'ptd' || actionBean.pengguna.perananUtama.kumpBPEL eq 'pentadbirtanah'}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                <div align="center"><font color="#001848"><b> TANDATANGAN DIGITAL</b></font></div>
            </legend>
            <div class="content" align="center">            
                <br/>  

                <p>
                    <s:submit name="sign" id="save" value="T/tangan" class="btn"/>&nbsp;      
                </p>
        </fieldset>
    </div>
</c:if>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JANA DOKUMEN</b></font></div>
        </legend>
        <div class="content" align="center">            
            <br/>  

            <p>
                <s:button name="jana" id="jana" value="Borang 4De" class="btn" onclick="showReport('${actionBean.noFail}', 'DISB4De_baru_MLK.rdf')"/>&nbsp;
                <s:button name="jana" id="jana" value="Borang P2e" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_BorangP2e_baru_MLK.rdf')"/>&nbsp;
            </p>
    </fieldset>
</div>
</stripes:form>


