<%--
    Document   : betulcukai
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : mohd.fairul
--%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<style type="text/css">
    td.s{
        font-weight:bold;
        color:blue;
        text-align: right;
    }
</style>

<script type="text/javascript">
    $(document).ready(function(){
        $('#flag').val('UC');
    });
   
    function isNumberKey(evt)
    {
        var charCode = (evt.which) ? evt.which : event.keyCode
        if (charCode > 31 && (charCode < 45 || charCode > 57 || charCode == 47))
            return false;

        return true;
    }

    function viewHakmilik(id){
        window.open("${pageContext.request.contextPath}/daftar/kesinambungan?viewhakmilikDetail&idHakmilik="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
    
    function allow()
    {
        if($('input[name=allowundersix]').is(':checked')){     
            $('#flag').val('C');
        }
        
        else if($('input[name=allowundersix]').is(':unchecked')){     
            $('#flag').val('UC');
        }
    }


    <%--    function kiraCukaiKelompok(idHakmilik, i){
            var cukai = $("#cukai"+i).val();
            $.post('${pageContext.request.contextPath}/daftar/pembetulan/betul?kiraCukaiKelompok&idHakmilik='+idHakmilik,
            function(data){
                 if(cukai < data)
                {
                    alert('Cukai Hakmilik: '+[idHakmilik]+' yang dimasukkan kurang dari RM '+data);
                    $('#cukai'+i).val("");
                    return false;
                }
                else if (cukai == data){
                    $('#cukai'+i).val(convert(data,'cukai'+i));
                }
            }, 'html');
        }--%>
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">
<s:hidden name="flag" id="flag"/>
    <s:messages />
    <s:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Cukai
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>
            </p>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0" id="line">
                    <display:column title="Bil">${line_rowNum}</display:column>
                    <display:column   title="ID Hakmilik" >
                        <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                    </display:column>
                    <display:column title="Luas">
                        <s:format formatPattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp; ${line.hakmilik.kodUnitLuas.nama}&nbsp;
                    </display:column>
                    <display:column title="Cukai Asal Atas Geran (RM)">
                    <s:format formatPattern="#,##0.00" value="${line.hakmilik.cukai}"/>
                    </display:column>
                    <display:column title="Cukai Asal Atas Geran Baru (RM)">
                        <s:text name="cukai[${line_rowNum-1}]" id="cukai${line_rowNum-1}" formatPattern="#,##0.00"/>
                        <%--<s:text name="cukai[${line_rowNum-1}]" onblur="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}')" id="cukai${line_rowNum-1}"/>--%>
                    </display:column>
                    <display:column title="Cukai Tahunan / Cukai Semasa(RM)">
                    <s:format formatPattern="#,##0.00" value="${line.hakmilik.cukaiSebenar}"/>
                    </display:column>
                    <display:column title="Cukai Tahunan / Cukai Semasa Baru (RM)">
                        <s:text name="cukaiThn[${line_rowNum-1}]" id="cukaiThn${line_rowNum-1}" formatPattern="#,##0.00"/>
                        <%--<s:text name="cukai[${line_rowNum-1}]" onblur="kiraCukaiKelompok('${line.hakmilik.idHakmilik}','${line_rowNum-1}')" id="cukai${line_rowNum-1}"/>--%>
                    </display:column>    
                </display:table>
                <br>
                <p>
                     
                    <s:checkbox name="allowundersix" id="allowundersix" onclick="allow();"/>
                    <font color="black">Membenarkan Cukai Baru/Cukai Tahunan Baru bawah RM6 untuk kes remisyen.</font>
                </p>
                <p>
                    <s:button name="saveCukai" value="Simpan" class="btn" onclick="if(confirm('Adakah anda pasti?'))doSubmit(this.form, this.name,'page_div');"/>
                </p>
                <br/>
            </div>
        </fieldset>
    </div>
</s:form>