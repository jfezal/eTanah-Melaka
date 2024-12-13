<%--
    Document   :  laporan_tanahGSAKeadaanTanah.jsp
    Created on :  Jan 17, 2012, 10:50:13 AM
    Author     :  Shazwan
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PERIHAL LOT-LOT BERSEMPADAN</title>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<script type="text/javascript">
    var size = 0 ;
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
   
    $(document).ready(function() {
         
        //            maximizeWindow();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
       
        }); //END OF READY FUNCTION
         
        function refreshpage2(type){
            var url = '${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?refreshpage&type='+type;
             window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }
        
        function editSempadan(){
    <%--alert(id);--%>
            NoPrompt();
            var idHakmilik = $('#idHakmilik').val();
//            alert(idHakmilik);
             window.open("${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?showFormPopUp&idHakmilik="+idHakmilik, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
        }
        
        function deleteRowSmpdn(idKandungan,f,tName)
        {   
            NoPrompt();
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                 var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/laporan_tanahGSA?deleteRow&idKandungan='+idKandungan+'&tName='+tName,q,
                function(data){
                    $('#page_div').html(data);
                    
                }, 'html');
                
                 self.refreshpage2('lSempadan') ;
            }
        }
        
        function refreshpage(){
        NoPrompt();
        opener.refreshGSA('main');
        self.close();
        }
</script>
<body>
    <script>
        // Allow the user to be warned by default.
                var allowPrompt = true;
                
                window.onbeforeunload = WarnUser;
                function WarnUser()
                {   
                    if(allowPrompt)
                        refreshpage();
                    if(allowPrompt)
                    {
                        event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
                    }
                    else
                    {
                        // Reset the flag to its default value.
                        allowPrompt = true;
                    }
                }
                function NoPrompt()
                {
                    allowPrompt = false;
                }

    </script>
    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
    <s:form beanclass="etanah.view.stripes.pelupusan.LaporanTanahGSAPelupusanActionBean" name="form">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <div class="subtitle" id="main">
        <fieldset class="aras1">

            <div id="perihaltanah">
                <legend>Perihal Lot-Lot Bersempadan</legend>
                <div class="content" align="center">
                    <table class="tablecloth" border="0">
                    <tr>
                        <th>&nbsp;</th><th>No. Hakmilik</th><th>Kegunaan Tanah</th><th>Keadaan Tanah</th><th>Catatan</th><th>Milik Kerajaan</th><th>Tindakan</th>
                    </tr>
                             <%--UTARA--%>
                             <c:if test="${!empty actionBean.listLaporTanahSpdnU}">
                                 <c:set var="i" value="1" />
                                 <tr>
                                            <th rowspan="${actionBean.uSize}">
                                                Utara
                                            </th>
                                    <c:forEach items="${actionBean.listLaporTanahSpdnU}" var="line">

                                            <td>
                                                ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                                <s:hidden  id="kandunganSpdnUHM${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.guna}
                                                <s:hidden   id="kandunganSpdnUKEG${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.guna" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.keadaanTanah}
                                                <s:hidden   id="kandunganSpdnUKEA${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                            </td>
                                            <td>
                                                ${line.laporanTanahSmpdn.catatan}
                                                <s:hidden   id="kandunganSpdnUCAT${i}" name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.catatan" />
                                            </td>
                                            <td>
                                                <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                                <s:hidden  name="listLaporTanahSpdnU[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnUMK${i}"/>

                                            </td>
                                            <td>
                                                <a onclick="deleteRowSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn},this.form,'mohonLaporSempadan')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                                <a onclick="editSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                           </td>
                                        </tr>

                                    <c:set var="i" value="${i+1}" />        
                                    </c:forEach>
                                </c:if>

                        <%--END OF UTARA--%>                             
                        <%--SELATAN--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnS}">
                        <c:set var="i" value="1" />
                        <tr>
                                            <th rowspan="${actionBean.sSize}">
                                               Selatan
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnS}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnSHM${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnSKEG${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.guna" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnSKEA${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.keadaanTanah" />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnSCAT${i}" name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.catatan" />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnS[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnSMK${i}"/>

                                    </td>
                                    <td>
                                        <a onclick="deleteRowSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn},this.form,'mohonLaporSempadan')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                   </td>
                                </tr>

                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                           </c:if>

                        <%--END OF SELATAN--%>
                        <%--TIMUR--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnT}">
                        <c:set var="i" value="1" />
                         <tr>
                                            <th rowspan="${actionBean.tSize}">
                                               Timur
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnT}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnTHM${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnTKEG${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnTKEA${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnTCAT${i}" name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnT[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnTMK${i}" />

                                    </td>
                                    <td>
                                        <a onclick="deleteRowSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn},this.form,'mohonLaporSempadan')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                   </td>
                                </tr>

                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>

                        </c:if>

                         <%--END OF TIMUR--%>
                         <%--BARAT--%>
                        <c:if test="${!empty actionBean.listLaporTanahSpdnB}">
                        <c:set var="i" value="1" />
                        <tr>
                                            <th rowspan="${actionBean.bSize}">
                                               Barat
                                            </th>
                            <c:forEach items="${actionBean.listLaporTanahSpdnB}" var="line">

                                    <td>
                                        ${line.laporanTanahSmpdn.hakmilik.idHakmilik}
                                        <s:hidden  id="kandunganSpdnBHM${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.hakmilik.idHakmilik"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.guna}
                                        <s:hidden  id="kandunganSpdnBKEG${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.guna"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.keadaanTanah}
                                        <s:hidden  id="kandunganSpdnBKEA${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.keadaanTanah"  />
                                    </td>
                                    <td>
                                        ${line.laporanTanahSmpdn.catatan}
                                        <s:hidden  id="kandunganSpdnBCAT${i}" name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.catatan"  />
                                    </td>
                                    <td>
                                        <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'Y'}">Kerajaan</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'T'}">Milik</c:if>
                                                 <c:if test="${line.laporanTanahSmpdn.milikKerajaan eq 'N'}">--</c:if>
                                        <s:hidden name="listLaporTanahSpdnB[${i-1}].laporanTanahSmpdn.milikKerajaan" id="kandunganSpdnBMK${i}" />

                                    </td>
                                    <td>
                                        <a onclick="deleteRowSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn},this.form,'mohonLaporSempadan')" onmouseover="this.style.cursor='pointer';" ><img alt="Hapus" height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/not_ok.gif'/></a> |
                                        <a onclick="editSmpdn(${line.laporanTanahSmpdn.idLaporTanahSpdn})" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/></a>
                                  <%--menyimpanSmpdn('B',${i},this.form) --%>
                                    </td>
                                </tr>

                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>

                        </c:if>

                         <%--END OF BARAT--%>

                    </table>

                </div>
                <br/>
            </div>
        </fieldset>

    <fieldset class="aras1">
        <table  width="100%" border="0">
            <tr>
                <td align="center">
                    <s:button name="Tambah" id="save" value="Tambah" class="btn" onclick="editSempadan()"/>
                    <s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/>
                </td>
            </tr>
        </table>   
    </fieldset>
    </div>
    </s:form>
</body>

