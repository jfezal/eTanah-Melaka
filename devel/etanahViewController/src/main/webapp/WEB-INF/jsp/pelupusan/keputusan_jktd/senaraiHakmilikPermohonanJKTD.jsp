<%-- 
    Document   : senaraiHakmilikPermohonanMMK
    Created on : Mar 27, 2013, 12:17:01 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAKLUMAT KEPUTUSAN</title>
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
    function openFrame(type){
        window.open("${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?openFrame"
            +"&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
        //        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshRekodKeputusanMMKV2('main');

            
        self.close();
    }
     
    function selectCheckBox(f,kpsn,size)
    {
        NoPrompt();

        var cnt=0;
        var data = new Array() ;
  
        for(cnt=0;cnt<size;cnt++)
        {
            if(size==1){
                if(document.frm.checkmate.checked) {
                    //                    alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate.value ;
                }

            }
            else {
                if(document.frm.checkmate[cnt].checked) {
                    //                  alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?pilihTanah&item='+data+'&kpsn='+kpsn,q,
            function(data){
                $('#page_div').html(data);
                self.openFrame('saveHakmilik');
            }, 'html');
            
        }
    }
    function selectCheckBoxLulus(f,kpsn,size)
    {
        NoPrompt();

        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<size;cnt++)
        {
            if(size==1){
                if(document.frm.checkmate2.checked) {
                    //                    alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate2.value ;
                }

            }
            else {
                if(document.frm.checkmate2[cnt].checked) {
                    //                  alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate2[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?pilihTanah&item='+data+'&kpsn='+kpsn,q,
            function(data){
                $('#page_div').html(data);
                self.openFrame('saveHakmilik');
            }, 'html');
            
        }
    }
    function selectCheckBoxTolak(f,kpsn,size)
    {
        NoPrompt();

        var cnt=0;
        var data = new Array() ;
        for(cnt=0;cnt<size;cnt++)
        {
            if(size==1){
                if(document.frm.checkmate1.checked) {
                    //                    alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate1.value ;
                }

            }
            else {
                if(document.frm.checkmate1[cnt].checked) {
                    //                  alert(document.frm.checkmate[cnt].value) ;
                    data[cnt] = document.frm.checkmate1[cnt].value ;
                }
                else{
                    data[cnt] = "T" ;
                }
            }
        }
        if(confirm("Adakah anda pasti?")) {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/rekod_keputusanJKTDV2?pilihTanah&item='+data+'&kpsn='+kpsn,q,
            function(data){
                $('#page_div').html(data);
                self.openFrame('saveHakmilik');
            }, 'html');
            
        }
    }
    
    function refreshpage(){
        //        alert('aa');
        NoPrompt();
        opener.refreshRekodKeputusanJKTDV2('main');

            
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
    <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanJKTDV2ActionBean" name="frm">
        <s:errors/>
        <s:messages/>
        <div class="subtitle displaytag" align="center">
            <div id="senaraiHakmilik" class="subtitle">
                <fieldset class="aras1" id="locate">
                    <legend>Senarai Hakmilik Tiada Keputusan</legend>
                    <s:hidden name="sizeTiadaKeputusan" id="sizeTiadaKeputusan" value="${actionBean.sizeTiadaKeputusan}"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTiadaKeputusan}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/rekod_keputusanJKTDV2">
                        <display:column> <s:checkbox name="checkmate" id="checkmate" value="${line.id}"/></display:column>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                            <display:column title="No.Lot/PT">
                                <c:if test="${line.noLot eq null}">
                                    ${line.hakmilik.noLot}
                                </c:if>
                                <c:if test="${line.noLot ne null}">
                                    ${line.noLot}
                                </c:if>
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">
                                <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                    ${line.hakmilik.bandarPekanMukim.nama}
                                </c:if>
                                <c:if test="${line.noLot ne null}">
                                    ${line.bandarPekanMukimBaru.nama}
                                </c:if>                                
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        </c:if>
                    </display:table>
                    <c:if test="${actionBean.sizeTiadaKeputusan > 0}">
                        <div align="center">
                            <s:button name="pilihLulus" value="Lulus" class="btn" onclick="javascript:selectCheckBox(this.form,'L',${actionBean.sizeTiadaKeputusan});"/>
                            <s:button name="pilihTolak" value="Tolak" class="btn" onclick="javascript:selectCheckBox(this.form,'T',${actionBean.sizeTiadaKeputusan});"/>
                        </div>
                    </c:if>
                </fieldset>
            </div>

            <div id="senaraiHakmilikLulus" class="subtitle">
                <fieldset class="aras1" id="locate">
                    <legend>Senarai Hakmilik Lulus</legend>
                    <s:hidden name="sizeLulus" id="sizeLulus" value="${actionBean.sizeLulus}"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikLulus}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/rekod_keputusanJKTDV2">
                        <display:column> <s:checkbox name="checkmate1" id="checkmate1" value="${line.id}"/></display:column>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                            <display:column title="No.Lot/PT">
                                <c:if test="${line.noLot eq null}">
                                    ${line.hakmilik.noLot}
                                </c:if>
                                <c:if test="${line.noLot ne null}">
                                    ${line.noLot}
                                </c:if>
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">
                                <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                    ${line.hakmilik.bandarPekanMukim.nama}
                                </c:if>
                                <c:if test="${line.noLot ne null}">
                                    ${line.bandarPekanMukimBaru.nama}
                                </c:if>                                
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        </c:if>
                    </display:table>
                    <c:if test="${actionBean.sizeLulus > 0}">
                        <div align="center">
                            <s:button name="pilihTolak" value="Tolak" class="btn" onclick="javascript:selectCheckBoxTolak(this.form,'T',${actionBean.sizeLulus});"/>
                        </div>
                    </c:if>
                </fieldset>
            </div> 


            <div id="senaraiHakmilikTolak" class="subtitle">
                <fieldset class="aras1" id="locate">
                    <legend>Senarai Hakmilik Tolak</legend>
                    <s:hidden name="sizeTolak" id="sizeTolak" value="${actionBean.sizeTolak}"/>
                    <display:table class="tablecloth" name="${actionBean.senaraiHakmilikTolak}" pagesize="20" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/rekod_keputusanJKTDV2">
                        <display:column> <s:checkbox name="checkmate2" id="checkmate2" value="${line.id}"/></display:column>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="ID Hakmilik" >${line.hakmilik.idHakmilik}</display:column> 
                            <display:column title="No.Lot/PT">
                                <c:if test="${line.noLot eq null}">
                                    ${line.hakmilik.noLot}
                                </c:if>
                                <c:if test="${line.noLot ne null}">
                                    ${line.noLot}
                                </c:if>
                            </display:column>
                            <display:column title="Bandar/Pekan/Mukim">
                                <c:if test="${line.bandarPekanMukimBaru.nama eq null}">
                                    ${line.hakmilik.bandarPekanMukim.nama}
                                </c:if>
                                <c:if test="${line.bandarPekanMukimBaru.nama ne null}">
                                    ${line.bandarPekanMukimBaru.nama}
                                </c:if>                                
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="No" sortable="true">${line_rowNum}
                                <s:hidden name="x" class="x${line_rowNum -1}" value="${line.id}"/></display:column>
                            <c:if test="${actionBean.kelompok eq true}">
                                <display:column title="ID Permohonan" >${line.permohonan.idPermohonan}</display:column> 
                            </c:if>
                            <display:column title="No.Lot/PT" >${line.noLot}</display:column>
                            <display:column title="Bandar/Pekan/Mukim" >${line.bandarPekanMukimBaru.nama}</display:column>
                        </c:if>
                    </display:table>
                    <c:if test="${actionBean.sizeTolak > 0}">
                        <div align="center">
                            <s:button name="pilihLulus" value="Lulus" class="btn" onclick="javascript:selectCheckBoxLulus(this.form,'L',${actionBean.sizeTolak});"/>
                        </div>
                    </c:if>
                </fieldset>
            </div>
            <br/><center><s:button name="tutup" value="Tutup" class="btn" onclick="refreshpage()"/></center> 
        </div>
    </s:form>
</body>


