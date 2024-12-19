<%-- 
    Document   : 4ae
    Created on : Nov 5, 2015, 11:58:15 AM
    Author     : Hazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='fn'  uri='http://java.sun.com/jsp/jstl/functions'%>
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
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900px,height=700px,scrollbars=yes");
    }   
</script>
<s:useActionBean beanclass = "etanah.view.ListUtil" var="list"/>
<stripes:form beanclass="etanah.view.stripes.permit.DSPermitActionBean">
    <s:messages/>
    <s:hidden name="noFail" id="noFail" />
    <s:hidden name="noPermit" id="noPermit" />

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="left">                           
                <p>
                    <s:submit name="kembali" id="kembali" value="Kembali" class="btn"/>&nbsp;
                </p>
            </div>
            <br/>
            
            <legend>
                <td align="center"><font color="#001848">Kanun Tanah Negara</font></td>
            </legend>
            <br/>
            <div align="center"><font color="#001848"><b> Borang 4Ae</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b> (Jadual Keenam Belas)</b></font></div>
            <br/>
            <div align="center"><font color="#001848"><b>LESEN PENDUDUKAN SEMENTARA (Borang Am)</b></font></div>            
            <br/>       

            <div class="content" align="center">
                <table border="0" width="50%">
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. Fail</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.noFail}</td>
                    </tr>                       
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">No. L.P.S</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.kodPermit}  ${actionBean.noPermit}</td>                           
                    </tr>   
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Daerah</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                        <td>${actionBean.daerahNama}</td>     
                    </tr>
                    <tr>
                        <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tujuan Pendudukan</font></td>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>   
                        <td>${actionBean.maksud}</td>
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

            <div class="content" align="center" id="pihak">
                <table border="0" width="50%">
                    <tr>
                        <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Nama Pemegang Lesen</font></td>
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

            <br></br>
            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    adalah dengan ini diberi kebenaran untuk menduduki tanah yang diperihalkan di bawah ini, bagi maksud,
                    dan pada fi, yang disebut di atas. Pendudukan hendaklah tertakluk kepada peruntukan
                    yang dijadualkan di bawah ini dan kepada mana - mana peruntukan lain yang ditetapkan
                    oleh Kaedah.
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
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Tanah Kerajaan/Tanah Rizab/Tanah Lombong</font></td>
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
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">Keluasan Tanah yang diberikan untuk pendudukan</font></td>
                    <td><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">:</font> </td>
                    <td>${actionBean.luas} &nbsp;
                        <c:if test = "${actionBean.radio2 eq 'H'}">
                            Hektar
                        </c:if>
                        <c:if test = "${actionBean.radio2 eq 'M'}">
                            Meter Persegi
                        </c:if> 
                    </td>                       
                </tr>
            </table>
            <br/>

            <div class="content" align="center">
                <tr>
                <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                    (Pelan tanah, untuk maksud pengenalan, adalah dikeluarkan secara berasingan dalam Borang L1e)
                </font>
                </tr> 
            </div>
    </fieldset>
</div>
<br/>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> REKOD PEMBAHARUAN </b></font></div>
        </legend>
        <div class="content" align="left">
            <table border="0" width="70%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">PEMBAHARUAN PERTAMA</font></td>
                </tr>
                <tr>
                    <td>
                        Fi (RM) <fmt:formatNumber pattern="#,##0.00" value="${actionBean.b1}"/>
                        Di bayar melalui No Resit ${actionBean.n1}
                        bagi tempoh berakhir 31 Disember ${actionBean.t1}
                    </td>
                </tr>
            </table>
            <br/>

            <table border="0" width="70%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">PEMBAHARUAN KEDUA</font></td>
                </tr>
                <tr>
                    <td>
                        Fi (RM) <fmt:formatNumber pattern="#,##0.00" value="${actionBean.b2}"/>
                        Di bayar melalui No Resit ${actionBean.n2}
                        bagi tempoh berakhir 31 Disember ${actionBean.t2}
                    </td>
                </tr>
            </table>
            <br/>

            <table border="0" width="70%">
                <tr>
                    <td width="50%"><font style="font-size:13px; color:#003194; font-family:Tahoma; font-weight:bold;">PEMBAHARUAN KETIGA</font></td>
                </tr>
                <tr>
                    <td>
                        Fi (RM)<fmt:formatNumber pattern="#,##0.00" value="${actionBean.b3}"/>
                        Di bayar melalui No Resit ${actionBean.n3}
                        bagi tempoh berakhir 31 Disember ${actionBean.t3}
                    </td>
                </tr>
            </table>
            <br/>
    </fieldset>
</div>
<br/>

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JADUAL </b></font></div>
        </legend>
        <div class="content" align="left">
            <table border="0" width="85%">
                <tr>
                    <td width="100%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (1) Lesen ini akan bermula pada <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhMula}"/>
                            dan tamat tempoh pada <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.tarikhtamat}"/>.
                        </font>
                    </td>
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (2) Lesen ini *tidak boleh diserahak/boleh di bawah Kaedah ${actionBean.kaedah}.
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (3) Lesen ini hendaklah tamat sekiranya berlaku kematian orang itu, atau pembubaran badan, yang buat masa itu berhak
                            mendapat faedah darinya.
                        </font>
                    </td>                   
                </tr>
            </table>   
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (4) Tanah di bawah lesen ini tidak boleh digunakan : -
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="100%">           
                <tr>                 
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(a) bagi apa - apa maksud selain dari maksud yang disebut di atas.
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>                 
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(b) untuk menanam apa - apa tanaman kekal.
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>                 
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(c) untuk mendirikan apa - apa bangunan kekal atau
                            struktur kekal yang lain.
                        </font>
                    </td>                    
                </tr>
            </table>
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            (5) Lesen ini boleh dibatalkan :-
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="100%">           
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(a) dengan serta - merta dan tanpa apa - apa bayaran pampasan, apabila
                            berlaku perlanggaran mana - mana peruntukan yang kepadanya ia tertakluk.
                        </font>
                    </td>                   
                </tr>
            </table> 
            <table border="0" width="100%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(b) dengan bayaran pampasan (yang akan dipersetujui atau ditentukan mengikut 
                            peruntukan seksyen 434 Kanun Tanah Negara) pada bila - bila masa sebelum tamat tempoh.
                        </font>
                </tr>
            </table>
            <table border="0" width="85%"> 
                <tr>
                    <td width="50%">
                        <font style="font-size:13px; color:#001848; font-family:Tahoma; font-weight:bold;">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${actionBean.tambah}
                        </font>                       
                    </td>        

                </tr>
            </table>
            <br/>          
        </div>
    </fieldset>
</div>

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

<div class="subtitle">
    <fieldset class="aras1">
        <legend>
            <div align="center"><font color="#001848"><b> JANA DOKUMEN</b></font></div>
        </legend>
        <div class="content" align="center">            
            <br/>  

            <p>
                <s:button name="jana" id="jana" value="Borang 4Ae" class="btn" onclick="showReport('${actionBean.noFail}', 'DISB4Ae_baru_MLK.rdf')"/>&nbsp;
                <s:button name="jana" id="jana" value="Borang L1e" class="btn" onclick="showReport('${actionBean.noFail}', 'DIS_BorangL1e_baru_MLK.rdf')"/>&nbsp;
            </p>
    </fieldset>
</div>
</stripes:form>





