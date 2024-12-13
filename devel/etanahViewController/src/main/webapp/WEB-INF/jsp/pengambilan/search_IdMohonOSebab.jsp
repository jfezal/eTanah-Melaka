<%-- 
    Document   : search_IdMohonOSebab
    Created on : Sep 27, 2010, 5:44:54 PM
    Author     : massita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Id Permohonan Sebelum atau Nama Projek</title>
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
            $(document).ready(function() {
                maximizeWindow();
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

                //added to make auto select to check boxes based on id, provided at Id permohonan yang terdahulu
                // and at nama projek
                //if((document.getElementById("idMohon").value != '') ||(document.getElementById("sebabProjek").value != ""))
            <c:forEach items="${actionBean.listIdMohon}" var="line" >
                <c:set var="j" value="1"/>
                <c:forEach items="${line.senaraiHakmilik}" var="line2">                     
                    <c:forEach items="${actionBean.senaraiHakmilikPB}" var="line3">
                            if(document.getElementById("hak_"+('${line.idPermohonan}')+'${j}') != null){
                                var hakmilik =document.getElementById("hak_"+('${line.idPermohonan}')+'${j}').value;
                        <%--alert(hakmilik);--%>
                        <c:if test="${line2.hakmilik.idHakmilik eq line3.hakmilik.idHakmilik}">
                            <%--alert("Inside:"+${line2.hakmilik.idHakmilik});--%>
                                        document.getElementById("hak_"+('${line.idPermohonan}')+'${j}').checked = true;
                        </c:if>
                                }
                    </c:forEach>
                    <c:set var="j" value="${j+1}"/>
                    
                </c:forEach>                                
            </c:forEach>
                    //end
                });
                function test(){
                    var count = $("#hakmilikCount").val();
                    var hakmilikData="";
                    for(var i=1;i<=count;i++){
                        if(document.getElementById("hak_"+($("#selectedIdMohon").val())+i).checked == true){
                            if(i==1){
                                hakmilikData = document.getElementById("hak_"+($("#selectedIdMohon").val())+i).value;
                            }else{
                                hakmilikData = hakmilikData+","+document.getElementById("hak_"+($("#selectedIdMohon").val())+i).value;
                            }
                        }
                    }
                    opener.document.getElementById('idSblm').value = $("#selectedIdMohon").val();
                    opener.document.getElementById('namaProjek').value = $("#selectedNama").val();
                    opener.document.getElementById('hakmilikList').value = hakmilikData;
                    self.opener.hideMaklumatPemohon();
                    self.close();
                }


                function selectRadio(obj,count){
                    document.getElementById("selectedIdMohon").value=obj.id;
                    document.getElementById("selectedNama").value=obj.value;
                    document.getElementById("hakmilikCount").value=count;
                <c:forEach items="${actionBean.listIdMohon}" var="line" >
                            if('${line.idPermohonan}' == obj.id){
                <c:set var="j" value="1"/>
                    <c:forEach items="${line.senaraiHakmilik}" var="line2">
                                                if(document.getElementById("hak_"+('${line.idPermohonan}')+'${j}') != null){
                                                    document.getElementById("hak_"+('${line.idPermohonan}')+'${j}').disabled = true;
                                                }
                    <c:set var="j" value="${j+1}"/>
                </c:forEach>
                                            }else if('${line.idPermohonan}' != obj.id){
                <c:set var="k" value="1"/>
                    <c:forEach items="${line.senaraiHakmilik}" var="line2">
                                                if(document.getElementById("hak_"+('${line.idPermohonan}')+'${k}') != null){
                                                    document.getElementById("hak_"+('${line.idPermohonan}')+'${k}').disabled = true;
                                                }
                    <c:set var="k" value="${k+1}"/>
                </c:forEach>
                                            }
            </c:forEach>

                }

        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.pengambilan.permohonan_penarikanbalikActionBean">
                <%-- <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Kemasukan Untuk Carian Id Permohonan Terdahulu atau Nama Projek
                    </legend>
                    <s:hidden id="selectedIdMohon" name="selectedIdMohon" />
                    <s:hidden id="selectedNama" name="selectedNama" />
                    <s:hidden id="hakmilikCount" name="hakmilikCount" />

                    <table border="0" cellspacing="5" width="80%">
                        <tr>
                            <td align="left" width="30%" valign="top"><label style="clear: right"  >Id permohonan yang terdahulu :</label></td>
                            <td><s:text name="id" id="idMohon" onkeyup="this.value=this.value.toUpperCase();" size="30"/></td>
                        </tr>
                        <tr>
                            <td><label>Nama Projek :</label></td>
                            <td><s:text name="sebabProjek" id="sebabProjek"  onkeyup="this.value=this.value.toUpperCase();" size="30"/></td>
                        </tr>
                        <tr>
                            <td><label>&nbsp;</label></td>
                            <td><s:submit name="cariIdSebelumOSebab" value="Cari" class="btn"/>
                                <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/></td>
                        </tr>
                    </table>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">
                <c:if test="${fn:length(actionBean.listIdMohon) > 0}" >
                    <fieldset class="aras1">
                        <legend>senarai ID Permohonan Beserta Nama Projek</legend><br />
                        <p>
                            <display:table style="width:97%" class="tablecloth" name="${actionBean.listIdMohon}" pagesize="4" cellpadding="1" cellspacing="1" requestURI="/pengambilan/penarikanBalik" id="line">

                                <display:column> <s:radio name="radio_" id="${line.idPermohonan}" value="${line.sebab}" style="width:15px" onclick="javascript:selectRadio(this,${fn:length(line.senaraiHakmilik)})"/></display:column>
                                <iframe scrolling="yes">
                                    <display:column title="Id Permohonan Terdahulu" property="idPermohonan" style="width:20%;vertical-align:top;"/>
                                    <display:column title="Nama Projek" property="sebab" style="text-transform:uppercase;width:90%;vertical-align:top;" />
                                    <display:column title="Hakmilik" style="width:60%;vertical-align:top;">
                                        <c:set var="i" value="1"/>
                                        <display:table name="${line.senaraiHakmilik}" id="line1">
                                            <display:column>
                                                <s:checkbox name="chkbox" id="hak_${line.idPermohonan}${i}" value="${line1.hakmilik.idHakmilik}" disabled="true"/>
                                                <c:set var="i" value="${i+1}" />
                                            </display:column>
                                            <display:column property="hakmilik.idHakmilik" title="IdHakmilik"/>
                                        </display:table>
                                    </display:column>
                                </iframe>
                                <%--<iframe scrolling="yes">
                                    <display:column title="Id Permohonan Terdahulu" property="idPermohonan" style="width:20%;vertical-align:top;"/>
                                    <display:column title="Nama Projek" property="sebab" style="text-transform:uppercase;width:90%;vertical-align:top;" />
                                    <display:column title="Hakmilik" style="width:60%;vertical-align:top;">
                                        <c:set var="i" value="1"/>
                                        <display:table name="${line.senaraiHakmilik}" id="line1">
                                            <display:column>
                                                <c:forEach items="${actionBean.senaraiHakmilikPB}" var="list">
                                                    
                                                    <c:if test="${line1.hakmilik.idHakmilik == list.hakmilik.idHakmilik}">
                                                        <s:checkbox name="chkbox" id="hak_${line.idPermohonan}${i}" value="${line1.hakmilik.idHakmilik}" checked="true" disabled="true"/>
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:if>
                                                    <c:if test="${line1.hakmilik.idHakmilik != list.hakmilik.idHakmilik}">
                                                        <s:checkbox name="chkbox" id="hak_${line.idPermohonan}${i}" value="${line1.hakmilik.idHakmilik}" disabled="true"/>
                                                        <c:set var="i" value="${i+1}" />
                                                    </c:if>
                                                </c:forEach>
                                            </display:column>
                                            <display:column property="hakmilik.idHakmilik" title="IdHakmilik"/>
                                        </display:table>
                                    </display:column>
                                </iframe>--%>
                            </display:table>
                        </p>
                        <c:if test="${fn:length(actionBean.listIdMohon) > 0}" >
                            <p><label>&nbsp;</label>
                                <s:button name="simpanIdMohonOSebab" value="OK" id="simpanIdMohonOSebab" class="btn" onclick="javascript:test();"/>
                            </p>
                        </c:if>
                    </fieldset>
                </c:if>
            </s:form>
        </div>

    </body>
</html>
